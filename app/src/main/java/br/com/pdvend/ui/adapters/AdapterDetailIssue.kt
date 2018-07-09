package br.com.pdvend.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import br.com.pdvend.ui.fragments.PDVendBaseFragment

class AdapterDetailIssue(fragmentManager: FragmentManager
                         , private val listFrags: List<PDVendBaseFragment>) :
        FragmentStatePagerAdapter
        (fragmentManager) {

    override fun getItem(position: Int): Fragment = listFrags[position]

    override fun getCount(): Int = listFrags.size

    override fun getPageTitle(position: Int): CharSequence? = listFrags[position].fragmentTitle()
}