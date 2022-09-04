package com.example.canoeworld.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.canoeworld.R
import com.example.canoeworld.adapters.PageAdapter
import com.example.canoeworld.objects.Accomodation
import com.example.canoeworld.objects.Equipment
import com.example.canoeworld.objects.LakeItem
import com.example.canoeworld.objects.Route
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val LAKE_DISTRICT = "Districts"
private const val ROUTE = "Routes"
private const val ACCOMMODATION = "Accommodation"
private const val EQUIPMENT = "Equipment"
private const val TAG3 = "HostFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [HostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HostFragment : Fragment() {
    private lateinit var lakeDistrict  :  ArrayList<LakeItem>
    private lateinit var routes        :  ArrayList<Route>
    private lateinit var accommodation :  ArrayList<Accomodation>
    private lateinit var equipment     :  ArrayList<Equipment>
    private var viewPager              :  ViewPager2? = null
    private val          pages =
                            arrayListOf("Info", "Trasy", "Nocleg", "Sprzęt")
    private val          icons =
        arrayListOf(R.mipmap.info_icon_foreground, R.mipmap.route_icon_foreground,
                    R.mipmap.tent_icon_foreground, R.mipmap.equ_icon_foreground)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            lakeDistrict = it.getParcelableArrayList<LakeItem>(LAKE_DISTRICT) as ArrayList<LakeItem>
            routes = it.getParcelableArrayList<Route>(ROUTE) as ArrayList<Route>
            accommodation = it.getParcelableArrayList<Accomodation>(ACCOMMODATION) as ArrayList<Accomodation>
            equipment = it.getParcelableArrayList<Equipment>(EQUIPMENT) as ArrayList<Equipment>
        }
        Log.d(TAG3, "onCreate args: $arguments and $savedInstanceState")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d(TAG3, "view created!")
        return inflater.inflate(R.layout.fragment_host, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.viewHolder)
        Log.d(TAG3, "in created view: viewPager - $viewPager, and state - $savedInstanceState")
        if(savedInstanceState == null) {
            if (arguments != null) {
                val adapter = PageAdapter(this, lakeDistrict, routes, accommodation, equipment)
                viewPager!!.adapter = adapter
            } else {
                val adapter = PageAdapter(this)
                viewPager!!.adapter = adapter
            }

        }
        else {
            val adapter = PageAdapter(this, lakeDistrict, routes, accommodation, equipment)
            viewPager!!.adapter = adapter
            viewPager!!.currentItem = savedInstanceState.getInt("number", 0)
        }
        setPages(view)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(viewPager != null) {
            outState.putInt("number", viewPager!!.currentItem)
            Log.d(TAG3, "saveState ${viewPager!!.currentItem}")
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if(savedInstanceState != null){
            viewPager!!.setCurrentItem(savedInstanceState.getInt("number",0), true)
            Log.d(TAG3, "restoreState ${viewPager!!.currentItem}")
        }
    }

    private fun setPages(view: View) {
        val tableLayout = view.findViewById<TabLayout>(R.id.lakePages)
        TabLayoutMediator(tableLayout, viewPager!!) { tab, position ->
            val view = tab.setCustomView(R.layout.tab_layout)
            val icon = view.view.findViewById<ImageView>(R.id.tabIcon)
            icon.setImageResource(icons[position])
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            {
                val text  = view.view.findViewById<TextView>(R.id.tabText)
                text.text = pages[position]
            }
        }.attach()
        Log.d(TAG3, "page tabs set!")
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param lakeDistrict Parameter 1.
         * @return A new instance of fragment HostFragment.
         */
        @JvmStatic
        fun newInstance(lakeDistrict  :  ArrayList<LakeItem>,
                        routes        :  ArrayList<Route>,
                        accommodation :  ArrayList<Accomodation>,
                        equipment     :  ArrayList<Equipment>) =
            HostFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(LAKE_DISTRICT, lakeDistrict)
                    putParcelableArrayList(ROUTES, routes)
                    putParcelableArrayList(ACCOMMODATION, accommodation)
                    putParcelableArrayList(EQUIPMENT, equipment)
                }
            }
    }
}
