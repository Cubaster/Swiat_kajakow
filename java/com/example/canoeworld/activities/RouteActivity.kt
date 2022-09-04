package com.example.canoeworld.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.canoeworld.R
import com.example.canoeworld.fragments.RouteFrag

const val TAG3 = "RouteActivity"

class RouteActivity : AppCompatActivity() {
    private var name  : String? = null
    private var dist  : Float?  = null
    private var diff  : String? = null
    private var image : String? = null
    private var desc  : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)
        getRoute()

        supportFragmentManager.commit {
            val fragment = setFragment()
            setReorderingAllowed(true)
            replace(R.id.routeFrag, fragment)
            addToBackStack("route")
        }
        Log.d(TAG3, "onCreate route done!")
    }

    private fun getRoute(){
        if(intent.hasExtra("name")){
            name = intent.getStringExtra("name")!!
        }
        if(intent.hasExtra("dist")){
            dist = intent.getFloatExtra("dist", 0F)
        }
        if(intent.hasExtra("diff")){
            diff = intent.getStringExtra("diff")!!
        }
        if(intent.hasExtra("img")){
            image = intent.getStringExtra("img")!!
        }
        if(intent.hasExtra("desc")){
            desc = intent.getStringExtra("desc")!!
        }
        Log.d(TAG3, "Route params fetched")
    }

    private fun setFragment(): Fragment {
        val fragment = RouteFrag()
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putFloat("dist", dist!!)
        bundle.putString("diff", diff)
        bundle.putString("img", image)
        bundle.putString("desc", desc)
        fragment.arguments = bundle
        Log.d(TAG3, "Fragment set")
        return fragment
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d(TAG3, "Route Closed")
        finish()
    }
}