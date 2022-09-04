package com.example.canoeworld.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.canoeworld.objects.LakeItem
import com.example.canoeworld.R

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val LAKE_DISTRICT = "Districts"
private const val TAG = "AboutFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [AboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutFragment : Fragment() {
    private var param1: ArrayList<LakeItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelableArrayList(LAKE_DISTRICT)
        }
        Log.d(TAG, "onCreate args: $arguments")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val mView = inflater.inflate(R.layout.fragment_about, container, false)
        if(param1 != null) setView(mView)
        Log.d(TAG, "view created!")
        return mView
    }

    private fun setView(mView: View){
        val title = mView.findViewById<TextView>(R.id.lakeDistrictName)
        val image = mView.findViewById<ImageView>(R.id.lakeDistrictImage)
        val desc  = mView.findViewById<TextView>(R.id.lakeDistrictDescription)
        title.text = param1!![0].getName()
        image.setImageResource(resources.getIdentifier(param1!![0].getImageId(), "mipmap", requireContext().packageName))
        desc.text = param1!![0].getDescription()
        Log.d(TAG, "view set!")
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment AboutFragment.
         */
        @JvmStatic
        fun newInstance(param1: ArrayList<LakeItem>) =
            AboutFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(LAKE_DISTRICT, param1)

                }
            }
    }
}