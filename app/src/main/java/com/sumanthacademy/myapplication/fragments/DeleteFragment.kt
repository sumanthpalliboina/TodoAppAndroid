package com.sumanthacademy.myapplication.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sumanthacademy.myapplication.MainActivity
import com.sumanthacademy.myapplication.R
import com.sumanthacademy.myapplication.model.Todo
import com.sumanthacademy.myapplication.ViewModel.TodoLive
import com.sumanthacademy.myapplication.ViewModel.TodoViewModel
import com.sumanthacademy.myapplication.databinding.FragmentDeleteBinding
import com.sumanthacademy.myapplication.util.AppConstants


class DeleteFragment : BottomSheetDialogFragment(),View.OnClickListener {

    lateinit var deleteFragmentBinding:FragmentDeleteBinding
    lateinit var todoViewModel: TodoViewModel
    var todoPosition:Int = 0
    var deletingTodo: Todo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            todoPosition = it.getInt(AppConstants.POSITION)
            deletingTodo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(AppConstants.TODO, Todo::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.getParcelable(AppConstants.TODO)
            }
        }
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        deleteFragmentBinding = FragmentDeleteBinding.inflate(inflater,container,false)
        return deleteFragmentBinding.root
    }

    companion object {
        const val TAG = "DeleteFragment"
        @JvmStatic
        fun newInstance(position: Int, todo: Todo) =
            DeleteFragment().apply {
                arguments = Bundle().apply {
                    putInt(AppConstants.POSITION, position)
                    putParcelable(AppConstants.TODO, todo)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todoViewModel = ViewModelProvider(requireActivity()).get(TodoViewModel::class.java)
        deleteFragmentBinding.cancel.setOnClickListener {
            dismiss()
        }
        deleteFragmentBinding.delete.setOnClickListener {
            todoViewModel.deletedData.value = deletingTodo?.let { it }
                ?.let { TodoLive(true, it) }

            (requireActivity() as MainActivity)?.let {
                it.deleteTodo(todoPosition)
            }
            dismiss()
        }
    }

    override fun onClick(view: View?) {
        println("clicked")
        /*if (view != null) {
            when(view.id) {
                R.id.delete -> {
                    todoViewModel.deletedData.value = deletingTodo?.let { it }
                        ?.let { TodoLive(true, it) }

                    (requireActivity() as MainActivity)?.let {
                        it.deleteTodo(todoPosition)
                    }
                    dismiss()
                }
                R.id.cancel -> {
                    dismiss()
                }
          }
        } */
    }


}