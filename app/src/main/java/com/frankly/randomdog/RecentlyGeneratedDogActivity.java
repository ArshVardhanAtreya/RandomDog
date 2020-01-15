package com.frankly.randomdog;

import android.os.Bundle;
import android.text.Html;

import androidx.appcompat.app.AppCompatActivity;

import com.frankly.randomdog.randomdogapp.RandomDogApplication;
import com.frankly.randomdog.ui.recentlygenerateddog.RecentlyGeneratedDogFragment;

/**
 * @author: ArshVardhanAtreya <arshvardhan@yahoo.co.in>
 */

public class RecentlyGeneratedDogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recently_generated_dog_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RecentlyGeneratedDogFragment.newInstance())
                    .commitNow();
        }

        String title = "<font color='#000000'>" + RandomDogApplication.getContext().getString(R.string.my_recently_generated_dogs) + " </font>";
        getSupportActionBar().setTitle(Html.fromHtml(title));
    }
}
