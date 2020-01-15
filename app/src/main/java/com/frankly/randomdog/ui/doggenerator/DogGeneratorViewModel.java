package com.frankly.randomdog.ui.doggenerator;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

/**
 * @author: ArshVardhanAtreya <arshvardhan@yahoo.co.in>
 */

public class DogGeneratorViewModel extends ViewModel {

    private ObservableField<String> imageURL = new ObservableField<>();

    public DogGeneratorViewModel() {
    }

    public ObservableField<String> getImageURL() {
        return imageURL;
    }

    public void setImageURL(ObservableField<String> imageURL) {
        this.imageURL = imageURL;
    }
}
