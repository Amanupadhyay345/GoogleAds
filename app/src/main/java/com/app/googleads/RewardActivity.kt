package com.app.googleads

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.googleads.databinding.ActivityRewardBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class RewardActivity : AppCompatActivity() {

    lateinit var binding: ActivityRewardBinding
    private var rewardedAd: RewardedAd? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityRewardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadReward()

    }

    private fun loadReward() {
        var adRequest = AdRequest.Builder().build()
        RewardedAd.load(this,"ca-app-pub-3940256099942544/5224354917", adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Toast.makeText(this@RewardActivity,"onAdFailedToLoad",Toast.LENGTH_SHORT).show()
                rewardedAd = null
            }

            override fun onAdLoaded(ad: RewardedAd) {
                Toast.makeText(this@RewardActivity,"onAdLoaded",Toast.LENGTH_SHORT).show()
                rewardedAd = ad

                rewardedAd?.show(this@RewardActivity
                ) { p0 -> Toast.makeText(this@RewardActivity, "" + p0.amount, Toast.LENGTH_SHORT).show() }
            }
        })
    }


}