package com.example.canoeworld.adapters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.canoeworld.fragments.AboutFragment
import com.example.canoeworld.fragments.AccommodationFragment
import com.example.canoeworld.fragments.EquipmentFragment
import com.example.canoeworld.fragments.RoutesFragment
import com.example.canoeworld.objects.Accomodation
import com.example.canoeworld.objects.Equipment
import com.example.canoeworld.objects.LakeItem
import com.example.canoeworld.objects.Route
import kotlin.coroutines.coroutineContext

private const val NUM_PAGES = 4
private const val LAKE_DISTRICT = "Districts"
private const val ROUTE = "Routes"
private const val ACCOMMODATION = "Accommodation"
private const val EQUIPMENT = "Equipment"
const val TAG4 = "PageAdapter"

class PageAdapter(fragment: Fragment,
                  private val districts     : ArrayList<LakeItem>?     = null,
                  private val routes        : ArrayList<Route>?        = null,
                  private val accommodation : ArrayList<Accomodation>? = null,
                  private val equipment     : ArrayList<Equipment>?    = null)
                  : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return NUM_PAGES
    }


    override fun createFragment(position: Int): Fragment {
        val fragment = when (position){
            0 -> AboutFragment()
            1 -> RoutesFragment()
            2 -> AccommodationFragment()
            else -> EquipmentFragment()
        }
        if(fragment is AboutFragment){
            val bundle = Bundle()
            bundle.putParcelableArrayList(LAKE_DISTRICT, districts)
            fragment.arguments = bundle
        }
        if(fragment is RoutesFragment){
            val bundle = Bundle()
            bundle.putParcelableArrayList(ROUTE, routes)
            fragment.arguments = bundle
        }
        if(fragment is AccommodationFragment){
            val bundle = Bundle()
            bundle.putParcelableArrayList(ACCOMMODATION, accommodation)
            fragment.arguments = bundle
        }
        if(fragment is EquipmentFragment){
            val bundle = Bundle()
            bundle.putParcelableArrayList(EQUIPMENT, equipment)
            fragment.arguments = bundle
        }
        Log.d(TAG4, "$position was chosen")
        return fragment
    }
}