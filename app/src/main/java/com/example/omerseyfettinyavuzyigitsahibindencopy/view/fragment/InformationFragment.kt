package com.example.omerseyfettinyavuzyigitsahibindencopy.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.omerseyfettinyavuzyigitsahibindencopy.R
import com.example.omerseyfettinyavuzyigitsahibindencopy.databinding.FragmentInformationPreviewBinding
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.hide
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.showToastShort
import com.example.omerseyfettinyavuzyigitsahibindencopy.view.activity.MainActivity


class InformationFragment : Fragment() {
    private var _binding: FragmentInformationPreviewBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInformationPreviewBinding.inflate(inflater, container, false)
        binding.apply {
            val advertName = arguments?.getString("advertName")
            val advertLink = arguments?.getString("advertLink")
            val advertPrice = arguments?.getString("advertPrice")
            val selectionCurrency = arguments?.getString("advertCurrency")
            selectionCurrency?.let { getCurrencyIcon(it) } // boş değilse
            advertNameTextView.text = advertName
            goAdvertTextView.text = advertLink
            priceTextView.text = advertPrice

            advertNameTextView.isSelected = true
            sellerNameTextView.isSelected = true
            categoryTextView.isSelected = true
            priceTextView.isSelected = true
            goAdvertTextView.isSelected = true
        }
        return binding.root
    }

    private fun getCurrencyIcon(selectionCurrency: String) {
        val resourceId =
            resources.getIdentifier(selectionCurrency.lowercase(), "drawable", requireContext().packageName)
        binding.currencyImageView.setImageResource(resourceId)
    }

    override fun onResume() {
        super.onResume()
        val activityBinding = (activity as MainActivity).binding
        activityBinding.apply {
            menuCategoryNameTextView.hide()
            nextPageImageView.setImageResource(R.drawable.arrow_right)
            binding.categoryTextView.text = menuCategoryNameTextView.text
        }
    }

}