package com.frankly.randomdog.ui.doggenerator;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.frankly.randomdog.BuildConfig;
import com.frankly.randomdog.R;
import com.frankly.randomdog.bitmaputil.BitmapCache;
import com.frankly.randomdog.databinding.DogGeneratorFragmentBinding;
import com.frankly.randomdog.model.DogModel;
import com.frankly.randomdog.networkresponse.GenerateDogResponse;
import com.frankly.randomdog.randomdogapp.RandomDogApplication;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * @author: ArshVardhanAtreya <arshvardhan@yahoo.co.in>
 */

public class DogGeneratorFragment extends Fragment {

    private ImageView dogImageView;
    private Button generateBtn;
    private DogGeneratorViewModel mViewModel;
    private ArrayList<DogModel> dogArrayList;
    private DogGeneratorFragmentBinding fragmentBinding;
    private View view;

    public static DogGeneratorFragment newInstance() {
        return new DogGeneratorFragment();
    }

    @BindingAdapter("bind:dogImage")
    public static void loadImage(ImageView iv, String url) {
        if (url != null && iv != null) {
            BitmapCache cache = RandomDogApplication.getCache();
            String key = url.substring(url.lastIndexOf("/") + 1);
            cache.setBitmapOrDownload(key, url, iv);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentBinding = DataBindingUtil.
                inflate(inflater, R.layout.dog_generator_fragment, container, false);
        view = fragmentBinding.getRoot();
        fragmentBinding.setViewModel(mViewModel);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DogGeneratorViewModel.class);
        dogImageView = view.findViewById(R.id.new_dog_imv);
        generateBtn = view.findViewById(R.id.generate_new_dogs_btn);
        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateNewDog();
            }
        });
        dogArrayList = getDogList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        updateDogList();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        loadImage(dogImageView, mViewModel.getImageURL().get());
    }

    private void generateNewDog() {
        // Instantiating the Volley Network Request
        RequestQueue queue = Volley.newRequestQueue(RandomDogApplication.getContext());
        String apiURL = "https://dog.ceo/api/breeds/image/random";

        // Requesting a string response from the above URL...
        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (BuildConfig.DEBUG)
                            Log.d("API_RESPONSE", response + "");
                        // Parsing the received string to get imageURL
                        Gson gson = new Gson();
                        GenerateDogResponse data = gson.fromJson(response, GenerateDogResponse.class);
                        if (!TextUtils.isEmpty(data.getStatus()) && data.getStatus()
                                .equalsIgnoreCase(getActivity().getString(R.string.success))) {
                            ObservableField<String> observableImageURL = new ObservableField<>();
                            observableImageURL.set(data.getMessage());
                            mViewModel.setImageURL(observableImageURL);
                            fragmentBinding.setViewModel(mViewModel);
                            loadImage(dogImageView, mViewModel.getImageURL().get());
                            if (!mViewModel.getImageURL().get().isEmpty())
                                dogArrayList.add(0, new DogModel(observableImageURL.get()));
                        } else {
                            if (BuildConfig.DEBUG)
                                Log.e("API_STATUS", data.getStatus() + "");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (BuildConfig.DEBUG)
                    Log.e("API_ERROR", "Error response from API...");
            }
        });

        // Adding the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private ArrayList<DogModel> getDogList() {
        ArrayList<DogModel> dogList;
        dogList = RandomDogApplication.getPref().loadDogsData();
        return dogList != null && dogList.size() > 0 ? dogList : new ArrayList<DogModel>();
    }

    private void updateDogList() {
        if (!dogArrayList.isEmpty())
            RandomDogApplication.getPref().saveDogsData(dogArrayList);
    }

}
