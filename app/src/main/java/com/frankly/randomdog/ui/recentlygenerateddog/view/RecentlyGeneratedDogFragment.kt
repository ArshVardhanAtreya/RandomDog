package com.frankly.randomdog.ui.recentlygenerateddog.view

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.frankly.randomdog.R
import com.frankly.randomdog.bitmaputil.BitmapCache
import com.frankly.randomdog.randomdogapp.RandomDogApplication
import com.frankly.randomdog.ui.recentlygenerateddog.viewmodel.RecentlyGeneratedDogViewModel

/**
 * @author: ArshVardhanAtreya <arshvardhan></arshvardhan>@yahoo.co.in>
 */
class RecentlyGeneratedDogFragment : Fragment() {
    private var mViewModel: RecentlyGeneratedDogViewModel? = null
    private var linearLayout: LinearLayout? = null
    private var deleteBtn: Button? = null
    private var rgdfView: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rgdfView = inflater.inflate(R.layout.recently_generated_dog_fragment, container, false)
        return rgdfView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(RecentlyGeneratedDogViewModel::class.java)
        mViewModel!!.setDogsList(RandomDogApplication.getPref()?.loadDogsData())
        setUIRef()
        bindHZSVData()
    }

    private fun setUIRef() {
        linearLayout = rgdfView?.findViewById<LinearLayout?>(R.id.layout_root)
        deleteBtn = rgdfView?.findViewById<Button?>(R.id.delete_btn)
        deleteBtn?.setOnClickListener {
            mViewModel = null
            RandomDogApplication.getPref()?.saveDogsData(null)
            bindHZSVData()
        }
    }

    private fun bindHZSVData() {
        if (mViewModel != null && mViewModel!!.getDogsList() != null && mViewModel!!.getDogsList()!!.size > 0) {
            for (i in mViewModel!!.getDogsList()!!.indices) {
                val itemLayout = LinearLayout(this@RecentlyGeneratedDogFragment.activity)
                itemLayout.id = i
                itemLayout.orientation = LinearLayout.HORIZONTAL
                val params = LinearLayout.LayoutParams(600, 600)
                params.gravity = Gravity.CENTER
                params.setMargins(5, 5, 5, 5)
                itemLayout.layoutParams = params
                val imageView = ImageView(this@RecentlyGeneratedDogFragment.activity)
                imageView.layoutParams = params
                loadImage(mViewModel!!.getDogsList()?.get(i)?.dogImageKey, mViewModel!!.getDogsList()?.get(i)?.dogImageURL, imageView)
                itemLayout.addView(imageView)
                linearLayout?.addView(itemLayout)
            }
        } else {
            linearLayout?.removeAllViews()
        }
    }

    private fun loadImage(key: String?, url: String?, iv: ImageView?) {
        val cache: BitmapCache? = RandomDogApplication.getCache()
        if (cache != null) {
            if (cache.hasBitmap(key)) {
                val glideOptions = RequestOptions()
                glideOptions.placeholder(R.drawable.ic_launcher_background)
                glideOptions.override(200, 200)
                glideOptions.centerCrop()
                iv?.let {
                    RandomDogApplication.getContext()?.let { it1 ->
                        Glide.with(it1).asBitmap()
                                .load(cache.getBitmap(key))
                                .apply(glideOptions)
                                .into(it)
                    }
                }
            } else {
                cache.setBitmapOrDownload(key, url, iv)
            }
        }
    }

    companion object {
        fun newInstance(): RecentlyGeneratedDogFragment? {
            return RecentlyGeneratedDogFragment()
        }
    }
}