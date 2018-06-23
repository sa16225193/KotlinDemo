package com.example.liuyong.kotlindemo.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.complex.fragment.BookCoverFragment
import com.example.complex.fragment.BookDetailFragment

class GoodsPagerAdapter(fm: FragmentManager, private val titles: MutableList<String>) : FragmentPagerAdapter(fm) {

    //根据位置序号分别指定不同的Fragment碎片类
    override fun getItem(position: Int): Fragment = when (position) {
        0 -> BookCoverFragment()
        1 -> BookDetailFragment()
        else -> BookCoverFragment()
    }

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence = titles[position]
}
