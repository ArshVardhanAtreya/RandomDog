package com.frankly.randomdog.networkresponse

/**
 * @author: ArshVardhanAtreya <arshvardhan></arshvardhan>@yahoo.co.in>
 */
class GenerateDogResponse(private val message: String?, private val status: String?) {
    fun getMessage(): String? {
        return message
    }

    fun getStatus(): String? {
        return status
    }

}