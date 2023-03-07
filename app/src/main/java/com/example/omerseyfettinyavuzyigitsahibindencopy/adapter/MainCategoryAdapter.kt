package com.example.omerseyfettinyavuzyigitsahibindencopy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.omerseyfettinyavuzyigitsahibindencopy.databinding.ItemMainCategoryBinding
import com.example.omerseyfettinyavuzyigitsahibindencopy.model.CategoryData
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.onItemClickListener

class MainCategoryAdapter(
    private val items: List<CategoryData>,
    val listener: onItemClickListener
) : RecyclerView.Adapter<MainCategoryAdapter.ListViewHolder>() {
    class ListViewHolder(val binding: ItemMainCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view =
            ItemMainCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = items[position]
        holder.binding.apply {
            categoryNameTextView.text = currentItem.name
        }
        holder.itemView.setOnClickListener {
            listener.onItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}