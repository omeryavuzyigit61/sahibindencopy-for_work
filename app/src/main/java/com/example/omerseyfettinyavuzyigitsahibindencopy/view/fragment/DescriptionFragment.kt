package com.example.omerseyfettinyavuzyigitsahibindencopy.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.omerseyfettinyavuzyigitsahibindencopy.R
import com.example.omerseyfettinyavuzyigitsahibindencopy.databinding.FragmentDescriptionAdvertBinding
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.showToastShort


class DescriptionFragment : Fragment() {

    private var _binding: FragmentDescriptionAdvertBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionAdvertBinding.inflate(layoutInflater, container, false)
        binding.apply {
            val advertDescription = arguments?.getString("advertDescription")
            descriptionTextView.text = advertDescription
        }
        return binding.root
    }
}