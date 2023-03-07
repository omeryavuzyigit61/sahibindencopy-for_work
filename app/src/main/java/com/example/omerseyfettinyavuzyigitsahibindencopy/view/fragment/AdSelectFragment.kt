package com.example.omerseyfettinyavuzyigitsahibindencopy.view.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.omerseyfettinyavuzyigitsahibindencopy.R
import com.example.omerseyfettinyavuzyigitsahibindencopy.databinding.FragmentAdSelectBinding
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.hide
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.show
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.showToastShort
import com.example.omerseyfettinyavuzyigitsahibindencopy.view.activity.MainActivity

class AdSelectFragment : Fragment() {

    private var _binding: FragmentAdSelectBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdSelectBinding.inflate(layoutInflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Reklam"
        binding.apply {

            val checkBoxList = listOf(
                mainPageCheckBox,
                categoryAdCheckBox,
                upperCheckBox,
                currentCheckBox,
                alertCheckBox
            )
            checkBoxList.forEach { checkBox ->
                checkBox.setOnClickListener {
                    checkBoxList.forEach { otherCheckBox ->
                        if (otherCheckBox != checkBox) {
                            otherCheckBox.isChecked = false
                        }
                    }
                }
            }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val activityBinding = (activity as MainActivity).binding
        activityBinding.apply {
            val advertPicture = arguments?.getParcelable<Uri>("advertPicture")
            val imageBundle = bundleOf(
                "advertPicture" to advertPicture
            )
            nextPageImageView.setImageResource(R.drawable.tick)
            nextPageImageView.setOnClickListener {
                if (binding.termsCheckBox.isChecked) {
                    findNavController().navigate(R.id.action_adSelectFragment_to_finishFragment,imageBundle)
                } else {
                    requireContext().showToastShort("Lütfen önce koşulları kabul ediniz")
                }
            }
            progressBar.progress = 4
            nextPageImageView.show()
            backPageImageView.show()
            backPageImageView.setOnClickListener {
                findNavController().navigate(R.id.action_adSelectFragment_to_previewAdvertFragment)
            }
        }
    }
}