package com.frankly.randomdog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author: ArshVardhanAtreya <arshvardhan@yahoo.co.in>
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void generateDog(View view) {
        Intent dogGeneratorIntent = new Intent(this, DogGeneratorActivity.class);
        startActivity(dogGeneratorIntent);
    }

    public void recentlyGenerateDog(View view) {
        Intent dogGeneratorIntent = new Intent(this, RecentlyGeneratedDogActivity.class);
        startActivity(dogGeneratorIntent);
    }
}
