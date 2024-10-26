package com.sumanthacademy.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sumanthacademy.myapplication.ViewModel.TodoViewModel
import com.sumanthacademy.myapplication.databinding.FragmentDeleteBinding


class DeleteFragment : BottomSheetDialogFragment(),View.OnClickListener {

    lateinit var deleteFragmentBinding:FragmentDeleteBinding
    lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }

    override fun onClick(view: View?) {

    }
}