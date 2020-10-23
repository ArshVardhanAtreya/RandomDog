package com.frankly.randomdog.model


/**
 * @author: ArshVardhanAtreya <arshvardhan></arshvardhan>@yahoo.co.in>
 */
class DogModel(val dogImageURL: String?) {
    val dogImageKey: String? = dogImageURL?.substring(dogImageURL.lastIndexOf("/") + 1)
    fun getImageURL(): String? {
        return dogImageURL
    }

    fun getKey(): String? {
        return dogImageKey
    }

}