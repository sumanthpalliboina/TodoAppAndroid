package com.sumanthacademy.myapplication.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sumanthacademy.myapplication.model.Todo

data class TodoLive(
    var isDeleted:Boolean,
    var todo: Todo
)

data class TodoRemainder(
    var isSet:Boolean,
    var todo: Todo
)

class TodoViewModel : ViewModel() {
    var deletedData = MutableLiveData<TodoLive>()
    var remainderData = MutableLiveData<TodoRemainder>()
}