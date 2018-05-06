package com.byy.mvvm_interviewtest.util

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by fq on 2017/11/27.
 */

/*class LongPreference(var context: Context,var name :String,var default:Long):ReadWriteProperty<Any?,Long>{

    val sharedPreferences by lazy { context.getSharedPreferences("defaulte",Context.MODE_PRIVATE) }
    override fun getValue(thisRef: Any?, property: KProperty<*>): Long {
          return  sharedPreferences.getLong(name,default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        sharedPreferences.edit().putLong(name,value).apply()
    }

}*/
class Preference<T>(var context: Context, var name: String, var default: T) : ReadWriteProperty<Any?, T> {

    private val sharedPreferences by lazy { context.getSharedPreferences("default", Context.MODE_PRIVATE) }
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name, default)
    }

    private fun findPreference(name: String, default: T): T = with(sharedPreferences) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalAccessException("this type cann't be saved into preferences")
        }
        res as T
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    private fun putPreference(name: String, default: T) = with(sharedPreferences.edit()) {
        when (default) {
            is Long -> putLong(name, default)
            is String -> putString(name, default)
            is Int -> putInt(name, default)
            is Boolean -> putBoolean(name, default)
            is Float -> putFloat(name, default)
            else -> throw IllegalAccessException("this type cann't be saved into preferences")
        }

    }.apply()

}