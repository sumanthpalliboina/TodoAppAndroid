package com.sumanthacademy.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.sumanthacademy.myapplication.interfaces.OnTodoClickListener
import com.sumanthacademy.myapplication.interfaces.OnTodoDeleteClickListener
import com.sumanthacademy.myapplication.interfaces.OnTodoRemainderClickListener
import com.sumanthacademy.myapplication.model.Todo
import com.sumanthacademy.myapplication.util.setSafeOnClickListener

class TodoAdapter(
    val context: Context,
    val itemsList:ArrayList<Todo>,
    private var todoClickListener: OnTodoClickListener,
    private var onDeleteTodoClickListener: OnTodoDeleteClickListener,
    private var onTodoRemainderClickListener: OnTodoRemainderClickListener
    ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class TodoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.title)
        val status = itemView.findViewById<TextView>(R.id.status)
        val deleteIcon = itemView.findViewById<ImageView>(R.id.delIcon)
        val remainderIcon = itemView.findViewById<ImageView>(R.id.remainderIcon)
        val itemTodo = itemView.findViewById<RelativeLayout>(R.id.itemTodo)
        init {
            itemView.setSafeOnClickListener {
                absoluteAdapterPosition.let{
                    if (it > -1 && itemsList.isNotEmpty()){
                        itemsList[it].let { it1 ->
                            todoClickListener.onTodoItemClickListener(it,it1)
                        }
                    }
                }
            }

            deleteIcon.setSafeOnClickListener {
                absoluteAdapterPosition.let {
                    if (it > -1 && itemsList.isNotEmpty()){
                        onDeleteTodoClickListener.deleteTodoClickListener(it)
                    }
                }
            }

            remainderIcon.setSafeOnClickListener {
                absoluteAdapterPosition.let {
                    if (it > -1 && itemsList.isNotEmpty()) {
                        onTodoRemainderClickListener.todoRemainderClickListener(it)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item,parent,false)
        return TodoViewHolder(view)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var item = itemsList.get(position)
        (holder as TodoViewHolder).also{
            it.title.text = item.title.toString() ?: "default"
            it.status.text = item.status.toString() ?: "default"

            try {
                when(item.status.lowercase()){
                    "Not Started Yet".lowercase() -> {
                        println("not started")
                        context?.let { it1 ->
                            var drawable = ContextCompat.getDrawable(it1,R.drawable.card_todo_bg) as GradientDrawable   //here drawable is shape
                            drawable.setColor(Color.parseColor(it1.getString(R.color.white)))
                            it.itemTodo.background = drawable
                        }
                        it.title.setTextColor(R.color.black)
                        it.status.setTextColor(R.color.black)
                        context?.let { it1 ->
                            var drawable = AppCompatResources.getDrawable(it1,R.drawable.baseline_delete_24)    //here drawable is vector (icon)
                            drawable?.let { it2 ->
                                var wrappedDrawable = DrawableCompat.wrap(it2)
                                DrawableCompat.setTint(wrappedDrawable,ContextCompat.getColor(it1,R.color.black))
                                it.deleteIcon.setImageDrawable(wrappedDrawable)
                            }
                        }
                        context?.let { it1 ->
                            var drawable = AppCompatResources.getDrawable(it1,R.drawable.baseline_alarm_24)
                            drawable?.let{ it2 ->
                                var wrappedDrawable = DrawableCompat.wrap(it2)
                                DrawableCompat.setTint(wrappedDrawable,ContextCompat.getColor(it1,R.color.black))
                                it.remainderIcon.setImageDrawable(wrappedDrawable)
                            }
                        }
                    }
                    "inProgress".lowercase() -> {
                        context?.let { it1 ->
                            var drawable = ContextCompat.getDrawable(it1,R.drawable.card_todo_bg) as GradientDrawable
                            drawable.setColor(Color.parseColor(it1.getString(R.color.in_progress)))
                            it.itemTodo.background = drawable
                        }
                        it.title.setTextColor(R.color.white)
                        it.status.setTextColor(R.color.white)
                        context?.let { it1 ->
                            var drawable = AppCompatResources.getDrawable(it1,R.drawable.baseline_delete_24)
                            drawable?.let { it2 ->
                                var wrappedDrawable = DrawableCompat.wrap(it2)
                                DrawableCompat.setTint(wrappedDrawable,ContextCompat.getColor(it1,R.color.white))
                                it.deleteIcon.setImageDrawable(wrappedDrawable)
                            }
                        }
                        context?.let { it1 ->
                            var drawable = AppCompatResources.getDrawable(it1,R.drawable.baseline_alarm_24)
                            drawable?.let{ it2 ->
                                var wrappedDrawable = DrawableCompat.wrap(it2)
                                DrawableCompat.setTint(wrappedDrawable,ContextCompat.getColor(it1,R.color.white))
                                it.remainderIcon.setImageDrawable(wrappedDrawable)
                            }
                        }
                    }
                    "Completed".lowercase() -> {
                        context?.let { it1 ->
                            var drawable = ContextCompat.getDrawable(it1,R.drawable.card_todo_bg) as GradientDrawable
                            drawable.setColor(Color.parseColor(it1.getString(R.color.completed)))
                            it.itemTodo.background = drawable
                        }
                        it.title.setTextColor(R.color.white)
                        it.status.setTextColor(R.color.white)
                        context?.let { it1 ->
                            var drawable = AppCompatResources.getDrawable(it1,R.drawable.baseline_delete_24)
                            drawable?.let { it2 ->
                                var wrappedDrawable = DrawableCompat.wrap(it2)
                                DrawableCompat.setTint(wrappedDrawable,ContextCompat.getColor(it1,R.color.white))
                                it.deleteIcon.setImageDrawable(wrappedDrawable)
                            }
                        }
                        context?.let { it1 ->
                            var drawable = AppCompatResources.getDrawable(it1,R.drawable.baseline_alarm_24)
                            drawable?.let{ it2 ->
                                var wrappedDrawable = DrawableCompat.wrap(it2)
                                DrawableCompat.setTint(wrappedDrawable,ContextCompat.getColor(it1,R.color.white))
                                it.remainderIcon.setImageDrawable(wrappedDrawable)
                            }
                        }
                    }
                }
            }catch(e:Exception){
                e.printStackTrace()
            }

            /*it.cardView.setOnClickListener(object:View.OnClickListener{
                override fun onClick(p0: View?) {
                    println("item clicked")
                    val activity = context as? AppCompatActivity
                    val fragmentManager = activity?.supportFragmentManager
                    val editFragment = EditFragment.newInstance(item.title.toString() ?: "default",item.status.toString() ?: "default")
                    if (fragmentManager != null) {
                        editFragment.show(fragmentManager,EditFragment.TAG)
                    }
                }

            })*/
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun addTodo(newTodo: Todo) {
        /*this.itemsList.add(newTodo)*/
        notifyDataSetChanged()
    }

    fun editTodo(position: Int,updatedTodo: Todo){
        /*this.itemsList[position].title = updatedTodo.title.toString()
        this.itemsList[position].status = updatedTodo.status.toString()*/
        notifyItemChanged(position)
    }

    fun removeTodo(position: Int){
        if (position != -1){
            notifyItemRemoved(position)
        }
    }
}