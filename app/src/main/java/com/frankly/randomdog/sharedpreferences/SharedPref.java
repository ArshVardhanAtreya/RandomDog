package com.frankly.randomdog.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.frankly.randomdog.R;
import com.frankly.randomdog.model.DogModel;
import com.frankly.randomdog.randomdogapp.RandomDogApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * @author: ArshVardhanAtreya <arshvardhan@yahoo.co.in>
 */


public class SharedPref {

    private Context context;

    public SharedPref(Context context) {
        this.context = context;
    }

    public void saveDogsData(ArrayList<DogModel> list) {
        SharedPreferences sharedPreferences = context.
                getSharedPreferences(RandomDogApplication.getContext().
                        getString(R.string.pref_name), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = null;
        if (list != null && list.size() > 0) {
            json = gson.toJson(list);
        }
        editor.putString(RandomDogApplication.getContext().getString(R.string.dogs_list), json);
        editor.apply();
    }

    public ArrayList<DogModel> loadDogsData() {
        SharedPreferences sharedPreferences = context.
                getSharedPreferences(RandomDogApplication.getContext().
                        getString(R.string.pref_name), MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(RandomDogApplication.getContext()
                .getString(R.string.dogs_list), null);
        Type type = new TypeToken<ArrayList<DogModel>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

}
