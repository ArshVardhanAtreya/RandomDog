package com.randomdog.ui.recentlygenerateddog.viewmodel

import androidx.lifecycle.ViewModel
import com.randomdog.model.DogModel
import java.util.*

/**
 * @author: ArshVardhanAtreya <arshvardhan></arshvardhan>@yahoo.co.in>
 */
class RecentlyGeneratedDogViewModel : ViewModel() {
    private var dogsList: ArrayList<DogModel?>? = null
    fun getDogsList(): ArrayList<DogModel?>? {
        return dogsList
    }

    fun setDogsList(dogsList: ArrayList<DogModel?>?) {
        this.dogsList = dogsList
    }
}