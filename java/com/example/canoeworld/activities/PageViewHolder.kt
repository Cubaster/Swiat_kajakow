package com.example.canoeworld.activities

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.canoeworld.database.DBHelper
import com.example.canoeworld.fragments.HostFragment
import com.example.canoeworld.R
import com.example.canoeworld.json.WeatherService
import com.example.canoeworld.objects.*

const val TAG2 = "PageViewHolder"

class PageViewHolder : AppCompatActivity() {
    private var          lakeDistrict   : Int = 0
    private lateinit var dbHelper       : DBHelper
    private lateinit var districts      : ArrayList<LakeItem>
    private lateinit var routes         : ArrayList<Route>
    private lateinit var accommodation  : ArrayList<Accomodation>
    private lateinit var equipment      : ArrayList<Equipment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_view_holder)
        Log.d(TAG2, "onCreate started")

        getFromIntent()

        dbHelper      = DBHelper(applicationContext)
        districts     = dbHelper.getLakeDistrict(lakeDistrict)
        routes        = dbHelper.getRoutes(lakeDistrict)
        accommodation = dbHelper.getAccommodation(lakeDistrict)
        equipment     = dbHelper.getEquipment(lakeDistrict)


        supportFragmentManager.commit {
            val fragment = setFragment()
            setReorderingAllowed(true)
            replace(R.id.hostContainer, fragment)
            addToBackStack("host")
        }

        this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    private fun getFromIntent(){
        if(intent.hasExtra("LakeDistrict")){
            lakeDistrict = intent.getIntExtra("LakeDistrict", 0)
        }
        Log.d(TAG2, "Receive lake district position")
    }

    private fun setFragment(): Fragment {
        val fragment = HostFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList("Districts", districts)
        bundle.putParcelableArrayList("Routes", routes)
        bundle.putParcelableArrayList("Accommodation", accommodation)
        bundle.putParcelableArrayList("Equipment", equipment)
        fragment.arguments = bundle
        Log.d(TAG2, "fragment bundle set")
        return fragment
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}