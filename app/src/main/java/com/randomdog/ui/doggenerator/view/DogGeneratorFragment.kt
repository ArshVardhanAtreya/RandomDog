package com.randomdog.ui.doggenerator.view

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.randomdog.BuildConfig
import com.randomdog.R
import com.randomdog.bitmaputil.BitmapCache
import com.randomdog.databinding.DogGeneratorFragmentBinding
import com.randomdog.model.DogModel
import com.randomdog.networkresponse.GenerateDogResponse
import com.randomdog.randomdogapp.RandomDogApplication
import com.randomdog.ui.doggenerator.viewmodel.DogGeneratorViewModel
import java.util.*

/**
 * @author: ArshVardhanAtreya <arshvardhan></arshvardhan>@yahoo.co.in>
 */

class DogGeneratorFragment : Fragment() {
    private var dogImageView: ImageView? = null
    private var generateBtn: Button? = null
    private var mViewModel: DogGeneratorViewModel? = null
    private var dogArrayList: ArrayList<DogModel?>? = null
    private var fragmentBinding: DogGeneratorFragmentBinding? = null
    private var dgfView: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.dog_generator_fragment, container, false)
        dgfView = fragmentBinding?.root
        fragmentBinding?.viewModel = mViewModel
        return dgfView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(DogGeneratorViewModel::class.java)
        dogImageView = dgfView?.findViewById<ImageView?>(R.id.new_dog_imv)
        generateBtn = dgfView?.findViewById<Button?>(R.id.generate_new_dogs_btn)
        generateBtn?.setOnClickListener { generateNewDog() }
        dogArrayList = getDogList()
    }

    override fun onDestroy() {
        super.onDestroy()
        updateDogList()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        loadImage(dogImageView, mViewModel?.getImageURL()?.get())
    }

    private fun generateNewDog() {
        // Instantiating the Volley Network Request
        val queue = Volley.newRequestQueue(RandomDogApplication.getContext())
        val apiURL = "https://dog.ceo/api/breeds/image/random"

        // Requesting a string response from the above URL...
        val stringRequest = StringRequest(Request.Method.GET, apiURL,
                Response.Listener { response ->
                    if (BuildConfig.DEBUG) Log.d("API_RESPONSE", response + "")
                    // Parsing the received string to get imageURL
                    val gson = Gson()
                    val data = gson.fromJson(response, GenerateDogResponse::class.java)
                    if (!TextUtils.isEmpty(data.getStatus()) && data.getStatus()
                                    .equals(activity?.getString(R.string.success), ignoreCase = true)) {
                        val observableImageURL = ObservableField<String?>()
                        observableImageURL.set(data.getMessage())
                        mViewModel?.setImageURL(observableImageURL)
                        fragmentBinding?.viewModel = mViewModel
                        loadImage(dogImageView, mViewModel?.getImageURL()?.get())
                        if (mViewModel?.getImageURL()?.get()?.isNotEmpty()!!) dogArrayList?.add(0, DogModel(observableImageURL.get()))
                    } else {
                        if (BuildConfig.DEBUG) Log.e("API_STATUS", data.getStatus() + "")
                    }
                }, Response.ErrorListener { if (BuildConfig.DEBUG) Log.e("API_ERROR", "Error response from API...") })

        // Adding the request to the RequestQueue.
        queue.add(stringRequest)
    }

    private fun getDogList(): ArrayList<DogModel?>? {
        val dogList: ArrayList<DogModel?> = RandomDogApplication.getPref()?.loadDogsData()!!
        return if (dogList != null && dogList.size > 0) dogList else ArrayList()
    }

    private fun updateDogList() {
        if (!dogArrayList?.isEmpty()!!) RandomDogApplication.getPref()?.saveDogsData(dogArrayList)
    }

    companion object {
        fun newInstance(): DogGeneratorFragment? {
            return DogGeneratorFragment()
        }

        @JvmStatic
        @BindingAdapter("bind:dogImage")
        fun loadImage(iv: ImageView?, url: String?) {
            if (url != null && iv != null) {
                val cache: BitmapCache? = RandomDogApplication.getCache()
                val key = url.substring(url.lastIndexOf("/") + 1)
                cache?.setBitmapOrDownload(key, url, iv)
            }
        }
    }
}