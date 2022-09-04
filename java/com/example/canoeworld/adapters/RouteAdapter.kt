package com.example.canoeworld.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.canoeworld.R
import com.example.canoeworld.activities.RouteActivity
import com.example.canoeworld.json.WeatherService
import com.example.canoeworld.objects.Cooridnates
import com.example.canoeworld.objects.Route

/**

 */
class RouteAdapter(
    private var values: ArrayList<Route>,
    private val context: Context
) : RecyclerView.Adapter<RouteAdapter.ViewHolder>() {
    private lateinit var weatherService: WeatherService
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_routes,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.mTitle.text = item.getName()
        val cords = Cooridnates(values[position].getLatitude(), values[position].getLongitude())
        weatherService = WeatherService(cords, context)
        weatherService.getWeather(holder.mImage)
        holder.listItem.setOnClickListener {
            val intent = Intent(context, RouteActivity::class.java).apply {
                putExtra("name", values[position].getName())
                putExtra("dist", values[position].getDistance())
                putExtra("diff", values[position].getDifficulty())
                putExtra("img", values[position].getImage())
                putExtra("desc", values[position].getDescription())
            }
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return values.size
    }

    inner class ViewHolder(binding: View) : RecyclerView.ViewHolder(binding) {
        val listItem: LinearLayout = binding.findViewById(R.id.routeItem)
        val mTitle = binding.findViewById<TextView>(R.id.routeName)!!
        val mImage = binding.findViewById<ImageView>(R.id.routeImg)!!
    }

}