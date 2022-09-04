package com.example.canoeworld.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.canoeworld.R

private const val PARAM = "route"
private const val TAG5 = "SingleRouteFragment"

class RouteFrag : Fragment() {
    private var name  : String? = null
    private var dist  : Float?  = null
    private var diff  : String? = null
    private var image : String? = null
    private var desc  : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name  = it.getString("name")
            dist  = it.getFloat("dist")
            diff  = it.getString("diff")
            image = it.getString("img")
            desc  = it.getString("desc")
        }
        Log.d(TAG5, "onCreate args: $arguments")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = inflater.inflate(R.layout.fragment_route, container, false)
        if(arguments != null) setView(mView)
        Log.d(TAG5, "view created!")
        return mView
    }

    @SuppressLint("SetTextI18n")
    fun setView(mView : View){
        val mName        = mView.findViewById<TextView>(R.id.rName)
        val mImage       = mView.findViewById<ImageView>(R.id.rImage)
        val mDistance    = mView.findViewById<TextView>(R.id.rDist)
        val mDifficulty  = mView.findViewById<TextView>(R.id.rDiff)
        val mDescription = mView.findViewById<TextView>(R.id.rDesc)
        mName.text = name;
        mImage.setImageResource(
            resources.getIdentifier(
                image,
                "mipmap",
                requireContext().packageName
            )
        )
        mDistance.text = "Dystans: $dist km"
        mDifficulty.text = "Trudność: $diff"
        mDescription.text = desc
        Log.d(TAG5, "view set!")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RouteFrag.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RouteFrag().apply {
                arguments = Bundle().apply {
                }
            }
    }
}