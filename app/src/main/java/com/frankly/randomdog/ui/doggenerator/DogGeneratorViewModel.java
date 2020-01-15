package com.frankly.randomdog.ui.doggenerator;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

public class DogGeneratorViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public DogGeneratorViewModel() {}

    public ObservableField<String> imageURL = new ObservableField<>();

    public ObservableField<String> getImageURL() {
        return imageURL;
    }

    public void setImageURL(ObservableField<String> imageURL) {
        this.imageURL = imageURL;
    }
}
