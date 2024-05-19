package com.app.googleads

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.googleads.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rewardapp.setOnClickListener {
            var intent = Intent(this,RewardActivity::class.java)
            startActivity(intent)
        }


        loadAds()

        loadInterstitalads()

    }

    private fun loadInterstitalads() {

        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
              Log.e("OnAdFailed",adError.toString())
                Toast.makeText(this@MainActivity, "OnAdFailed$adError",Toast.LENGTH_SHORT).show()
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.e("OnAdFailed","OnAddLoaded")
                Toast.makeText(this@MainActivity, "OnAdLOad",Toast.LENGTH_SHORT).show()
                mInterstitialAd = interstitialAd

                ShowAdd()
            }
        })
    }

    private fun ShowAdd() {

        if (mInterstitialAd !=null)
        {

            mInterstitialAd?.show(this)
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback(){
                override fun onAdClicked() {
                    super.onAdClicked()

                    Toast.makeText(this@MainActivity,"ONAddClicked",Toast.LENGTH_SHORT).show()
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()

                    Toast.makeText(this@MainActivity,"onAdDismissedFullScreenContent",Toast.LENGTH_SHORT).show()
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    super.onAdFailedToShowFullScreenContent(p0)

                    Toast.makeText(this@MainActivity,"onAdFailedToShowFullScreenContent",Toast.LENGTH_SHORT).show()
                }

                override fun onAdImpression() {
                    super.onAdImpression()

                    Toast.makeText(this@MainActivity,"onAdImpression",Toast.LENGTH_SHORT).show()
                }

                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()

                    Toast.makeText(this@MainActivity,"onAdShowedFullScreenContent",Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun loadAds() {

       MobileAds.initialize(this)
        var adrequest= AdRequest.Builder().build()
        binding.adView.loadAd(adrequest)

        binding.adView.adListener = object: AdListener() {
            override fun onAdClicked() {
               Toast.makeText(this@MainActivity,"OnAddClick",Toast.LENGTH_SHORT).show()
            }

            override fun onAdClosed() {
                Toast.makeText(this@MainActivity,"OnAddClosed",Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                Toast.makeText(this@MainActivity, "OnAddFaild$adError",Toast.LENGTH_SHORT).show()
                Log.e("OnAddFaild",""+adError.toString())
            }

            override fun onAdImpression() {
                Toast.makeText(this@MainActivity,"OnAddImpression",Toast.LENGTH_SHORT).show()
            }

            override fun onAdLoaded() {
                Toast.makeText(this@MainActivity,"OnAddLoad",Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                Toast.makeText(this@MainActivity,"OnAddOpen",Toast.LENGTH_SHORT).show()
            }
        }
    }
}