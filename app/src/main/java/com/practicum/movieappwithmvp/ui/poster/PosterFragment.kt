package com.practicum.movieappwithmvp.ui.poster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.practicum.movieappwithmvp.databinding.FragmentPosterBinding

class PosterFragment : Fragment() {

    companion object {
        private const val NUMBER = "poster"

        fun newInstance(poster: String) = PosterFragment().apply {
            arguments = Bundle().apply {
                putString(NUMBER, poster)
            }
        }
    }

    private lateinit var binding: FragmentPosterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentPosterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.postertext.text = requireArguments().getString(NUMBER).toString()
    }

}