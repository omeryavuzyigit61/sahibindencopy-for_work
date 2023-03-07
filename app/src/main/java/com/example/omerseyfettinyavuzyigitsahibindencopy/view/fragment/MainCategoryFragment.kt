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
import com.example.omerseyfettinyavuzyigitsahibindencopy.databinding.FragmentMainCategoryBinding
import com.example.omerseyfettinyavuzyigitsahibindencopy.model.CategoryData
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.hide
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.onItemClickListener
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.show
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.textString

class MainCategoryFragment : Fragment(), onItemClickListener {

    private var _binding: FragmentMainCategoryBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainCategoryBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "İlan Ver - Kategori Seçimi"
        binding.apply {
            categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val adapter = getAdapter()
        binding.categoryRecyclerView.adapter = adapter
        val activityBinding = (activity as MainActivity).binding
        activityBinding.apply {
            menuCategoryNameTextView.hide()
            menuCategoryNameTextView.text = "categoryName"
        }
    }

    private fun getData(): ArrayList<CategoryData> {
        val data = ArrayList<CategoryData>()
        data.add(CategoryData("Telefon"))
        data.add(CategoryData("Bilgisayar"))
        data.add(CategoryData("Diğer"))
        return data
    }

    private fun getAdapter(): MainCategoryAdapter {
        return MainCategoryAdapter(getData(), this)
    }

    override fun onItemClick(position: CategoryData) {
        val categoryBundle = bundleOf(
            "categoryName" to position.name)
        findNavController().navigate(R.id.action_mainCategoryFragment_to_secondCategoryFragment,categoryBundle)
    }

}