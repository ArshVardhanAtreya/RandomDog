package com.randomdog.ui.doggenerator.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

/**
 * @author: ArshVardhanAtreya <arshvardhan></arshvardhan>@yahoo.co.in>
 */
class DogGeneratorViewModel : ViewModel() {
    private var imageURL: ObservableField<String?>? = ObservableField()
    fun getImageURL(): ObservableField<String?>? {
        return imageURL
    }

    fun setImageURL(imageURL: ObservableField<String?>?) {
        this.imageURL = imageURL
    }
}