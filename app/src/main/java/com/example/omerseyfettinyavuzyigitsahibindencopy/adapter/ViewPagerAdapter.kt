package com.example.omerseyfettinyavuzyigitsahibindencopy.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.omerseyfettinyavuzyigitsahibindencopy.view.fragment.DescriptionFragment
import com.example.omerseyfettinyavuzyigitsahibindencopy.view.fragment.InformationFragment
import com.example.omerseyfettinyavuzyigitsahibindencopy.view.fragment.PreviewAdvertFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle,val bundle: Bundle,val description:String) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                PreviewAdvertFragment.newInstance(bundle)
            }
            1 -> {
                PreviewAdvertFragment.newDescritpino(description)
            }
            else -> {
                Fragment()
            }
        }
    }
}
