package com.sumanthacademy.myapplication

import android.content.Context
import android.content.DialogInterface
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.sumanthacademy.myapplication.ViewModel.TodoLive
import com.sumanthacademy.myapplication.ViewModel.TodoViewModel
import com.sumanthacademy.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),View.OnClickListener,OnTodoClickListener,OnTodoDeleteClickListener {

    lateinit var activityMainBinding: ActivityMainBinding
    var todoItems:ArrayList<Todo> = ArrayList<Todo>()
    lateinit var todoAdapter:TodoAdapter
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate")

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = activityMainBinding.root
        setContentView(view)

        setLayoutManger()
        persistTodos()
        activityMainBinding.todosRecyclerView.setHasFixedSize(false)
        activityMainBinding.todosRecyclerView.itemAnimator = null
        todoAdapter = TodoAdapter(this,todoItems,this,this)
        activityMainBinding.todosRecyclerView.adapter = todoAdapter

        setListeners()
        PopupIntro(this,packageName).showDialog(){ it ->
            println("response from popup intro -> ${it}")
        }

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        todoViewModel.deletedData.observe(this){ it ->
            Toast.makeText(this,"${it.todo.title} is deleted",Toast.LENGTH_SHORT).show()
        }
    }

    fun setLayoutManger(){
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            activityMainBinding.todosRecyclerView.layoutManager = GridLayoutManager(this,2)
        }else{
            activityMainBinding.todosRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setLayoutManger()
    }

    fun persistTodos(){
        todoItems = SPUtil.getTodos()
    }

    fun hideKeyboard(){
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activityMainBinding.input.windowToken,0)
    }

    fun addTodo(){
        val text = activityMainBinding.input.text.toString()
        if (text.isNotEmpty()){
            todoItems.apply {
                add(Todo(R.drawable.sumanth_photo_qqqzsw,text,"Not Started Yet"))
            }
            todoAdapter.addTodo(Todo(R.drawable.sumanth_photo_qqqzsw,text,"Not Started Yet"))
            activityMainBinding.input.setText("")
            hideKeyboard()
            SPUtil.saveTodos(todoItems)
        }else{
            hideKeyboard()
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Empty Input")
            dialogBuilder.setIcon(R.drawable.baseline_warning_24)
            dialogBuilder.setMessage("Please Provide Your input")
            dialogBuilder.setPositiveButton("Ok",DialogInterface.OnClickListener{dialog,which ->
                dialog.dismiss()
            })
            dialogBuilder.setNegativeButton("Cancel",DialogInterface.OnClickListener{dialog,which ->
                showSnackbar()
                dialog.dismiss()
            })
            val dialog = dialogBuilder.create()
            dialog.show()
        }
    }

    fun showSnackbar(){
        val snackbar = Snackbar.make(activityMainBinding.main,"Provide your valid input",Snackbar.LENGTH_SHORT)
        snackbar.setAction("close"){
            snackbar.dismiss()
        }
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.view.setBackgroundColor(Color.RED)
        snackbar.setTextColor(Color.WHITE)
        snackbar.show()
    }

    override fun onStart() {
        super.onStart()
        println("onStart")
    }

    override fun onResume() {
        super.onResume()
        println("onResume")
    }

    override fun onPause() {
        super.onPause()
        println("onPause")
    }

    override fun onStop() {
        super.onStop()
        println("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }

    fun setListeners(){
        activityMainBinding.addBtn.setOnClickListener(this)
        /*activityMainBinding.input.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE){
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(activityMainBinding.input.windowToken,0)
                true
            }else{
                false
            }
        }*/
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when(view.id){
                R.id.addBtn -> addTodo()
            }
        }
    }

    override fun onTodoItemClickListener(position: Int, tod: Todo) {
        var item = todoItems[position]
        item.let{
            val fragmentManager = supportFragmentManager
            val editFragment = EditFragment.newInstance(position,item.title.toString() ?: "default",item.status.toString() ?: "default")
            if (fragmentManager != null){
                editFragment.show(fragmentManager,EditFragment.TAG)
                editFragment.isCancelable = false
            }
        }
    }

    fun editAndSaveTodo(position: Int,todo:Todo){
        this.todoItems[position].title = todo.title.toString()
        this.todoItems[position].status = todo.status.toString()
        todoAdapter.editTodo(position,todo)
        SPUtil.saveTodos(todoItems)
    }

    override fun deleteTodoClickListener(position: Int) {
        var item = todoItems[position]
        item.let {
            val fragmentManager = supportFragmentManager
            val deleteFragment = DeleteFragment.newInstance(position,item)
            if (fragmentManager != null) {
                deleteFragment.show(fragmentManager,DeleteFragment.TAG)
                //deleteFragment.isCancelable = false
            }
        }
    }

    fun deleteTodo(position: Int){
        this.todoItems.removeAt(position)
        todoAdapter.removeTodo(position)
        SPUtil.saveTodos(this.todoItems)
    }

    fun showBottomSheetDialogForDelete(position:Int){
        var dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.fragment_delete,null)
        val closeBtn = view.findViewById<AppCompatButton>(R.id.cancel)
        val deleteBtn = view.findViewById<AppCompatButton>(R.id.delete)
        val title = view.findViewById<AppCompatTextView>(R.id.myTitle)
        val status = view.findViewById<AppCompatTextView>(R.id.myStatus)

        val item = this.todoItems[position]

        title.text = item.title.toString()
        status.text =item.status.toString()

        closeBtn.setOnClickListener {
            dialog.dismiss()
        }

        deleteBtn.setOnClickListener{
            todoViewModel.deletedData.value = TodoLive(true,this.todoItems[position])
            deleteTodo(position)
            dialog.dismiss()
        }

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        dialog.setContentView(view)

        dialog.setCancelable(true) //false -> this will prevent the user drag down the dialog to dismiss
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

}