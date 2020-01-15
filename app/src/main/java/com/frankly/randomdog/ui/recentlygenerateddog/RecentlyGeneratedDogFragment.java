package com.frankly.randomdog.ui.recentlygenerateddog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.frankly.randomdog.R;
import com.frankly.randomdog.bitmaputil.BitmapCache;
import com.frankly.randomdog.randomdogapp.RandomDogApplication;

/**
 * @author: ArshVardhanAtreya <arshvardhan@yahoo.co.in>
 */

public class RecentlyGeneratedDogFragment extends Fragment {

    private RecentlyGeneratedDogViewModel mViewModel;
    private LinearLayout linearLayout;
    private Button deleteBtn;
    private View view;

    public static RecentlyGeneratedDogFragment newInstance() {
        return new RecentlyGeneratedDogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recently_generated_dog_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RecentlyGeneratedDogViewModel.class);
        mViewModel.setDogsList(RandomDogApplication.getPref().loadDogsData());
        setUIRef();
        bindHZSVData();
    }

    private void setUIRef() {
        linearLayout = view.findViewById(R.id.layout_root);
        deleteBtn = view.findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel = null;
                RandomDogApplication.getPref().saveDogsData(null);
                bindHZSVData();
            }
        });
    }

    private void bindHZSVData() {

        if (mViewModel != null && mViewModel.getDogsList() != null
                && mViewModel.getDogsList().size() > 0) {

            for (int i = 0; i < mViewModel.getDogsList().size(); i++) {
                LinearLayout itemLayout = new LinearLayout(RecentlyGeneratedDogFragment.this.getActivity());
                itemLayout.setId(i);
                itemLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(600, 600);
                params.gravity = Gravity.CENTER;
                params.setMargins(5, 5, 5, 5);
                itemLayout.setLayoutParams(params);

                ImageView imageView = new ImageView(RecentlyGeneratedDogFragment.this.getActivity());
                imageView.setLayoutParams(params);
                loadImage(mViewModel.getDogsList().get(i).getKey(), mViewModel.getDogsList().get(i).getImageURL(), imageView);

                itemLayout.addView(imageView);
                linearLayout.addView(itemLayout);

            }
        } else {
            linearLayout.removeAllViews();
        }
    }

    private void loadImage(String key, String url, ImageView iv) {
        BitmapCache cache = RandomDogApplication.getCache();

        if (cache.hasBitmap(key)) {
            RequestOptions glideOptions = new RequestOptions();
            glideOptions.placeholder(R.drawable.ic_launcher_background);
            glideOptions.override(200, 200);
            glideOptions.centerCrop();
            Glide.with(RandomDogApplication.getContext()).asBitmap()
                    .load(cache.getBitmap(key))
                    .apply(glideOptions)
                    .into(iv);
        } else {
            cache.setBitmapOrDownload(key, url, iv);
        }

    }

}
