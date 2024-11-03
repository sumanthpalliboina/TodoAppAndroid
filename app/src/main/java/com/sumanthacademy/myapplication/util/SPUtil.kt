package com.sumanthacademy.myapplication.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sumanthacademy.myapplication.App
import com.sumanthacademy.myapplication.model.Todo

class SPUtil {
    companion object{
        fun setString(key:String,value:String){
            val sp = App.sharedPref
            val editor = sp.edit()
            editor.putString(key,value)
            editor.apply()
        }
        fun getString(key:String):String{
            return try{
                val sp = App.sharedPref
                val encryptedValue = sp.getString(key,"dummy")
                encryptedValue!!
            }catch(e:Exception){
                ""
            }
        }
        fun saveTodos(todos:ArrayList<Todo>){
            val sp = App.sharedPref
            val editor = sp.edit()
            editor.putString("todos",Gson().toJson(todos))
            editor.apply()
        }

        fun getTodos():ArrayList<Todo>{
            return try{
                val sp = App.sharedPref
                val savedData = sp.getString("todos","")
                val type = object : TypeToken<ArrayList<Todo>>(){}.type
                Gson().fromJson(savedData,type)
            }catch (e:Exception){
                ArrayList()
            }
        }
    }
}