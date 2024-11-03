package com.sumanthacademy.myapplication.interfaces

import com.sumanthacademy.myapplication.model.Todo

interface OnTodoClickListener {
    fun onTodoItemClickListener(position:Int,tod: Todo)
}