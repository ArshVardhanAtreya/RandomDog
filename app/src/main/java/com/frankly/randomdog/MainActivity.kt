package com.frankly.randomdog

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.frankly.randomdog.ui.doggenerator.view.DogGeneratorActivity
import com.frankly.randomdog.ui.recentlygenerateddog.view.RecentlyGeneratedDogActivity

/**
 * @author: ArshVardhanAtreya <arshvardhan></arshvardhan>@yahoo.co.in>
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun generateDog(view: View?) {
        val dogGeneratorIntent = Intent(this, DogGeneratorActivity::class.java)
        startActivity(dogGeneratorIntent)
    }

    fun recentlyGenerateDog(view: View?) {
        val dogGeneratorIntent = Intent(this, RecentlyGeneratedDogActivity::class.java)
        startActivity(dogGeneratorIntent)
    }
}