package com.example.akstask.view.productDetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter



class PicsPagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return ProductPicFragment.newInstance()
    }

    override fun getCount(): Int {
        return 4
    }
}