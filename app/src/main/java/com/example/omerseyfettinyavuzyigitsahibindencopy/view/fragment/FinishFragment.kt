package com.example.omerseyfettinyavuzyigitsahibindencopy.view.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.omerseyfettinyavuzyigitsahibindencopy.R
import com.example.omerseyfettinyavuzyigitsahibindencopy.databinding.FragmentFinishBinding
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.hide
import com.example.omerseyfettinyavuzyigitsahibindencopy.view.activity.MainActivity

class FinishFragment : Fragment() {

    private var _binding: FragmentFinishBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinishBinding.inflate(layoutInflater, container, false)
        binding.apply {
            (activity as AppCompatActivity).supportActionBar?.title = "Tebrikler"
            imageView7.setOnClickListener {
                findNavController().navigate(R.id.action_finishFragment_to_emtyPageFragment)
            }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val activityBinding = (activity as MainActivity).binding
        val advertPicture = arguments?.getParcelable<Uri>("advertPicture")
        binding.apply {
            advertImageView.setImageURI(advertPicture)
        }
        activityBinding.apply {
            progressBar.progress = 5
            nextPageImageView.hide()
            backPageImageView.hide()
        }
    }

}