package com.frankly.randomdog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         *         https://dog.ceo/api/breeds/image/random
         *         {"message":"https:\/\/images.dog.ceo\/breeds\/terrier-patterdale\/patterdale-terrier-287612805105275kBT.jpg","status":"success"}
         *
         *
         * */
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
