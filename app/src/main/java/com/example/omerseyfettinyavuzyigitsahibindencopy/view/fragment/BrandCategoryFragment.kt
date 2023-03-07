package com.example.omerseyfettinyavuzyigitsahibindencopy.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.omerseyfettinyavuzyigitsahibindencopy.R
import com.example.omerseyfettinyavuzyigitsahibindencopy.adapter.MainCategoryAdapter
import com.example.omerseyfettinyavuzyigitsahibindencopy.view.activity.MainActivity
import com.example.omerseyfettinyavuzyigitsahibindencopy.databinding.FragmentBrandCategoryBinding
import com.example.omerseyfettinyavuzyigitsahibindencopy.model.CategoryData
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.hide
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.onItemClickListener

class BrandCategoryFragment : Fragment(), onItemClickListener {

    private var _binding: FragmentBrandCategoryBinding? = null
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBrandCategoryBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "İlan Ver - Kategori Seçimi"
        binding?.apply {
            brandRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        val adapter = getAdapter()
        binding?.brandRecyclerView?.adapter = adapter
        val categoryName = arguments?.getString("categoryName").toString()
        val activityBinding = (activity as MainActivity).binding
        activityBinding.menuCategoryNameTextView.text="${activityBinding.menuCategoryNameTextView.text} > ${categoryName}"
        activityBinding.backPageImageView.setOnClickListener {
            findNavController().navigate(R.id.action_brandCategoryFragment_to_secondCategoryFragment)
        }
        activityBinding.nextPageImageView.hide()
        activityBinding.progressBar.progress = 1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getData(): ArrayList<CategoryData> {
        val data = ArrayList<CategoryData>()
        data.add(CategoryData("Samsung"))
        data.add(CategoryData("Sony"))
        data.add(CategoryData("Philips"))
        data.add(CategoryData("LG"))
        data.add(CategoryData("Vestel"))
        data.add(CategoryData("Arçelik"))
        data.add(CategoryData("Altus"))
        data.add(CategoryData("Beko"))
        return data
    }

    private fun getAdapter(): MainCategoryAdapter {
        return MainCategoryAdapter(getData(), this)
    }

    override fun onItemClick(position: CategoryData) {
        val categoryBundle = bundleOf(
            "categoryName" to position.name
        )
        findNavController().navigate(R.id.action_brandCategoryFragment_to_detailFragment,categoryBundle)
    }
}