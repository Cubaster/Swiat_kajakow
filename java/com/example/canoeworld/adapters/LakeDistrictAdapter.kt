package com.example.canoeworld.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.canoeworld.activities.PageViewHolder
import com.example.canoeworld.objects.PlaceholderContent.PlaceholderItem
import com.example.canoeworld.R


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 **/
const val TAG2 = "LakeAdapter"

class LakeDistrictAdapter(
    private val values: List<PlaceholderItem>,
    private val context: Context
) : RecyclerView.Adapter<LakeDistrictAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG2, "viewHolder created")
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_lake_district_item,
            parent,
            false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.mTitle.text = item.title.replace(" ", "\n")
        holder.mImage.setImageResource(item.image)
        holder.mCard.setOnClickListener {
            startMainApp(position)
        }
        Log.d(TAG2, "viewHolder bind!")
    }

    private fun startMainApp(position: Int){
        Log.d(TAG2, "run PageHolder!")
        val intent = Intent(context, PageViewHolder::class.java).apply {
            putExtra("LakeDistrict", position)
        }
        context.startActivity(intent)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: View) :
        RecyclerView.ViewHolder(binding) {
        val mTitle = binding.findViewById<TextView>(R.id.lake_district_title)!!
        val mImage = binding.findViewById<ImageView>(R.id.lake_district_image)!!
        val mCard = binding.findViewById<CardView>(R.id.lake_district_card)!!
    }

}