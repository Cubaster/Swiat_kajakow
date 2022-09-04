package com.example.canoeworld.adapters

import android.annotation.SuppressLint
import android.text.util.Linkify
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.canoeworld.R
import com.example.canoeworld.objects.Accomodation

const val TAG = "AccommodationAdapter"

class AccommodationAdapter(private var values: List<Accomodation>)
                           : RecyclerView.Adapter<AccommodationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "viewHolder created")
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_accommodation,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.mTitle.text = item.getName()
        holder.mRating.text = "Ocena: ${item.getRating()}/5.0"
        holder.mRoute.text = "Na trasie: ${item.getRoute()}"
        holder.mLink.text = item.getUrl()
        Linkify.addLinks(holder.mLink, Linkify.WEB_URLS)
        Log.d(TAG, "viewHolder bind!")
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "item counted")
        return values.size
    }

    inner class ViewHolder(binding: View) : RecyclerView.ViewHolder(binding) {
        val mTitle : TextView = binding.findViewById(R.id.accName)
        val mRating : TextView = binding.findViewById(R.id.accRate)
        val mRoute : TextView = binding.findViewById(R.id.accRoute)
        val mLink : TextView = binding.findViewById(R.id.accLink)
    }
}