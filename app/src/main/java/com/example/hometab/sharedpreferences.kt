package com.example.hometab

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class sharedpreferences(context: Context) {
    val preferences:SharedPreferences=context.getSharedPreferences("id_preferences",Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String) : String {
        return preferences.getString(key, defValue).toString()
    }

    fun setString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

//    var myedit: String?
//        get() = preferences.getString("id", "")
//        set(value) = preferences.edit().putString("id", value).apply()
}