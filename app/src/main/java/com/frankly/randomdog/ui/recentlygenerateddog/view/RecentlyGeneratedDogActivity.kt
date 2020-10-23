package com.frankly.randomdog.ui.recentlygenerateddog.view

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.frankly.randomdog.R
import com.frankly.randomdog.randomdogapp.RandomDogApplication

/**
 * @author: ArshVardhanAtreya <arshvardhan></arshvardhan>@yahoo.co.in>
 */
class RecentlyGeneratedDogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recently_generated_dog_activity)
        if (savedInstanceState == null) {
            RecentlyGeneratedDogFragment.newInstance()?.let {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, it)
                        .commitNow()
            }
        }
        val title = "<font color='#000000'>" + (RandomDogApplication.getContext()?.getString(R.string.my_recently_generated_dogs)
                ?: "No Data") + " </font>"
        supportActionBar?.title = Html.fromHtml(title)
    }
}