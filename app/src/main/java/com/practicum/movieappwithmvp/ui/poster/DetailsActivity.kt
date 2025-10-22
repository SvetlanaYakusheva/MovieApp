package com.practicum.movieappwithmvp.ui.poster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.practicum.movieappwithmvp.R
import com.practicum.movieappwithmvp.databinding.ActivityDetailsBinding



class DetailsActivity : AppCompatActivity() {

    private lateinit var tabMediator: TabLayoutMediator

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_details)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val poster = intent.getStringExtra("poster") ?: ""
        val movieId = intent.getStringExtra("id") ?: ""

        binding.viewPager.adapter = DetailsAdapter(
                supportFragmentManager,
                lifecycle,
                poster,
                movieId)

        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = "ПОСТЕР"
                1 -> tab.text = "О ФИЛЬМЕ"
            }
        }
        tabMediator.attach()
    }



    override fun onDestroy() {
        super.onDestroy()
        tabMediator.detach()
    }
}