package com.practicum.movieappwithmvp.ui.poster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.practicum.movieappwithmvp.databinding.FragmentDetailsBinding
import com.practicum.movieappwithmvp.databinding.FragmentPosterBinding

class DetailsFragment : Fragment() {

    companion object {
        private const val NUMBER = "details"

        fun newInstance(name: String) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putString(NUMBER, name)
            }
        }
    }

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieName.text = requireArguments().getString(NUMBER).toString()
    }

}