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
import com.example.omerseyfettinyavuzyigitsahibindencopy.adapter.ViewPagerAdapter
import com.example.omerseyfettinyavuzyigitsahibindencopy.databinding.FragmentPreviewAdvertBinding
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.show
import com.example.omerseyfettinyavuzyigitsahibindencopy.view.activity.MainActivity
import com.google.android.material.tabs.TabLayoutMediator


class PreviewAdvertFragment : Fragment() {

    private var _binding: FragmentPreviewAdvertBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPreviewAdvertBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Ön İzleme"
        binding.apply {
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val advertName = arguments?.getString("advertName")
        val advertLink = arguments?.getString("advertLink")
        val advertPrice = arguments?.getString("advertPrice")
        val advertDescription = arguments?.getString("advertDescription")
        val selectedCurrency = arguments?.getString("advertCurrency")
        val advertPicture = arguments?.getParcelable<Uri>("advertPicture")

        binding.advertPictureImageView.setImageURI(advertPicture)

        val myBundle = bundleOf(
            "advertName" to advertName,
            "advertLink" to advertLink,
            "advertPrice" to advertPrice,
            "advertCurrency" to selectedCurrency
        )
        val imageBundle = bundleOf(
            "advertPicture" to advertPicture
        )
        val activityBinding = (activity as MainActivity).binding
        activityBinding.apply {
            progressBar.progress = 3
            nextPageImageView.show()
            backPageImageView.show()
            backPageImageView.setOnClickListener {
                findNavController().navigate(R.id.action_previewAdvertFragment_to_detailFragment)
            }
            nextPageImageView.setOnClickListener {
                findNavController().navigate(R.id.action_previewAdvertFragment_to_adSelectFragment,imageBundle)
            }
            backPageImageView.setOnClickListener {

            }
        }
        binding.tabLayout.apply {
            val advertPageAdapter =
                advertDescription?.let {
                    ViewPagerAdapter(childFragmentManager, lifecycle, myBundle,
                        it
                    )
                }

            binding.previewViewPager.adapter = advertPageAdapter
            TabLayoutMediator(this, binding.previewViewPager) { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "İLAN BİLGİSİ"
                        InformationFragment().apply {
                            arguments = Bundle().apply {
                                putString("ARG_PARAM1", "uhdsuhcs")
                            }
                        }
                    }
                    1 -> {
                        tab.text = "İLAN AÇIKLAMA"

                    }
                }

            }.attach()
        }
    }

    companion object {
        fun newInstance(bundle: Bundle) =
            InformationFragment().apply {
                arguments = Bundle().apply {
                    putString("advertName", bundle.getString("advertName"))
                    putString("advertLink", bundle.getString("advertLink"))
                    putString("advertPrice", bundle.getString("advertPrice"))
                    putString("advertCurrency", bundle.getString("advertCurrency"))
                }
            }

        fun newDescritpino(description: String) = DescriptionFragment().apply {
            arguments = Bundle().apply {
                putString("advertDescription", description)
            }
        }
    }
}