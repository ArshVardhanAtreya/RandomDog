package com.frankly.randomdog.ui.recentlygenerateddog;

import androidx.lifecycle.ViewModel;

import com.frankly.randomdog.model.DogModel;

import java.util.ArrayList;

/**
 * @author: ArshVardhanAtreya <arshvardhan@yahoo.co.in>
 */

public class RecentlyGeneratedDogViewModel extends ViewModel {

    private ArrayList<DogModel> dogsList;

    public ArrayList<DogModel> getDogsList() {
        return dogsList;
    }

    public void setDogsList(ArrayList<DogModel> dogsList) {
        this.dogsList = dogsList;
    }
}
