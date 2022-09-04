package com.example.canoeworld.json

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.canoeworld.API_KEY
import com.example.canoeworld.objects.Cooridnates
import org.json.JSONObject
import org.json.JSONTokener
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class WeatherService(private val cords: Cooridnates,
                     private val context: Context){

    private val queue = Volley.newRequestQueue(context)

    fun getWeather(imageView: ImageView){
            var url ="https://api.openweathermap.org/data/2.5/weather?lat=${cords.latitude}&lon=${cords.longitude}&appid=$API_KEY"
            var request = StringRequest(
                Request.Method.GET,
                url,
                {
                        response ->
                    var weather = JSONTokener(response).nextValue() as JSONObject
                    var type    = weather.getJSONArray("weather").getJSONObject(0).getString("main")
                    val res = when(type){
                        "Clouds" -> "cloud_foreground"
                        "Storm" -> "storm_foreground"
                        "Snow" -> "snow_foreground"
                        "Rain" -> "rain_foreground"
                        else -> "sun_foreground"
                    }
                    imageView.setImageResource(context.resources.getIdentifier(res, "mipmap", context.packageName))
                },
                {
                    println("Not loaded!")
                }
            )
        queue.add(request)
        Log.d("WeatherService", "weather fetched")
    }
}

