package com.frankly.randomdog;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;

import com.frankly.randomdog.randomdogapp.RandomDogApplication;
import com.frankly.randomdog.ui.doggenerator.DogGeneratorFragment;

public class DogGeneratorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog_generator_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DogGeneratorFragment.newInstance())
                    .commitNow();
        }

        String title = "<font color='#000000'>" + RandomDogApplication.getContext().getString(R.string.generate_dogs) + " </font>";
        getSupportActionBar().setTitle(Html.fromHtml(title));
    }
}
