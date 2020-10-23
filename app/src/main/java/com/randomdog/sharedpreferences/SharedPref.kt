package com.randomdog.sharedpreferences

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.randomdog.R
import com.randomdog.model.DogModel
import com.randomdog.randomdogapp.RandomDogApplication
import java.util.*

/**
 * @author: ArshVardhanAtreya <arshvardhan></arshvardhan>@yahoo.co.in>
 */
class SharedPref(private val context: Context?) {
    fun saveDogsData(list: ArrayList<DogModel?>?) {
        val sharedPreferences = context?.getSharedPreferences(RandomDogApplication.getContext()!!.getString(R.string.pref_name), Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        val gson = Gson()
        var json: String? = null
        if (list != null && list.size > 0) {
            json = gson.toJson(list)
        }
        editor?.putString(RandomDogApplication.getContext()?.getString(R.string.dogs_list), json)
        editor?.apply()
    }

    fun loadDogsData(): ArrayList<DogModel?>? {
        val sharedPreferences = context?.getSharedPreferences(RandomDogApplication.getContext()?.getString(R.string.pref_name), Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences?.getString(RandomDogApplication.getContext()
                ?.getString(R.string.dogs_list), null)
        val type = object : TypeToken<ArrayList<DogModel?>?>() {}.type
        return gson.fromJson(json, type)
    }

}