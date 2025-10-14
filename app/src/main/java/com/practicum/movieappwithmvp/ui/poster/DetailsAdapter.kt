package com.practicum.movieappwithmvp.ui.poster

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailsAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle)
: FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> PosterFragment.newInstance("фото постера")
            else -> DetailsFragment.newInstance("тут будет название")
//            else -> NumberFragment.newInstance(position + 1)
        }
        //return NumberFragment.newInstance(position + 1)
    }
}