package com.practicum.movieappwithmvp.ui.poster

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.practicum.movieappwithmvp.R
import com.practicum.movieappwithmvp.databinding.ActivityDetailsBinding
import com.practicum.movieappwithmvp.presentation.poster.DetailsViewModel

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailsActivity : AppCompatActivity() {

    private lateinit var tabMediator: TabLayoutMediator
    private lateinit var poster: ImageView
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //poster = findViewById(R.id.poster)

       //val imageUrl = intent.extras?.getString("poster", "") ?: ""

//        val viewModel: DetailsViewModel by viewModel {
//            parametersOf(imageUrl)
//        }

//        viewModel.observeUrl().observe(this) {
//            setupPosterImage(it)
//        }

        binding.viewPager.adapter = DetailsAdapter(supportFragmentManager, lifecycle)

        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = "ПОСТЕР"
                1 -> tab.text = "О ФИЛЬМЕ"
            }
        }
        tabMediator.attach()
    }

    private fun setupPosterImage(url: String) {
        Glide.with(applicationContext)
            .load(url)
            .into(poster)
    }

    override fun onDestroy() {
        super.onDestroy()
        tabMediator.detach()
    }
}