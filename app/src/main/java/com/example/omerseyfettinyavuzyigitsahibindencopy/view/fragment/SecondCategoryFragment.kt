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
import com.example.omerseyfettinyavuzyigitsahibindencopy.databinding.FragmentSecondCategoryBinding
import com.example.omerseyfettinyavuzyigitsahibindencopy.model.CategoryData
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.hide
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.onItemClickListener
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.show

class SecondCategoryFragment : Fragment(), onItemClickListener {

    private var _binding: FragmentSecondCategoryBinding? = null
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondCategoryBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "İlan Ver - Kategori Seçimi"
        binding.let {
            it?.apply {
                secondRecyclerView.layoutManager = LinearLayoutManager(requireContext())

            }
        }
        return binding?.root
    }

    private var categoryName: String = ""
    override fun onResume() {
        super.onResume()
        val adapter = getAdapter()
        binding?.secondRecyclerView?.adapter = adapter
        categoryName = arguments?.getString("categoryName").toString()
        val activityBinding = (activity as MainActivity).binding
        activityBinding.menuCategoryNameTextView.text = categoryName
        activityBinding.menuCategoryNameTextView.show()
        activityBinding.backPageImageView.setOnClickListener {
            findNavController().navigate(R.id.action_secondCategoryFragment_to_mainCategoryFragment2)
        }
        activityBinding.nextPageImageView.hide()
        activityBinding.progressBar.progress = 1
    }

    private fun getData(): ArrayList<CategoryData> {
        val data = ArrayList<CategoryData>()
        data.add(CategoryData("Televizyon"))
        data.add(CategoryData("Medya Oynatıcı"))
        data.add(CategoryData("Monitor"))
        data.add(CategoryData("Drone"))
        data.add(CategoryData("Uydu Alıcısı"))
        data.add(CategoryData("Kulaklık"))
        data.add(CategoryData("Sanal Gerçeklik Gözlüğü"))
        data.add(CategoryData("Modem"))
        return data
    }

    private fun getAdapter(): MainCategoryAdapter {
        return MainCategoryAdapter(getData(), this)
    }

    override fun onItemClick(position: CategoryData) {
        val categoryBundle = bundleOf(
            "categoryName" to position.name
        )
        findNavController().navigate(
            R.id.action_secondCategoryFragment_to_brandCategoryFragment,
            categoryBundle
        )
    }

}