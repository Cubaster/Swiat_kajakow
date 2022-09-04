package com.example.canoeworld.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.canoeworld.database.DBHelper
import com.example.canoeworld.json.JsonHandler
import com.example.canoeworld.R

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var jsonHandler: JsonHandler
    private var loaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "OnCreate started")
        if(savedInstanceState == null) {
            dbHelper = DBHelper(applicationContext)
            jsonHandler = JsonHandler(dbHelper, applicationContext)
            getPref()
            if(!loaded){
                jsonHandler.loadItems()
                loaded = true
                setPref()
            }
        }

    }

    private fun setPref(){
         val sharedPref : SharedPreferences = this.getPreferences(MODE_PRIVATE)
         val editor = sharedPref.edit()
         editor.putBoolean("jsonState", loaded)
         editor.apply()
        Log.d(TAG, "state to cache")
    }

    private fun getPref(){
        val sharedPref = getPreferences(MODE_PRIVATE)
        loaded = sharedPref.getBoolean("jsonState", false)
        Log.d(TAG, "state from cache")
    }

}