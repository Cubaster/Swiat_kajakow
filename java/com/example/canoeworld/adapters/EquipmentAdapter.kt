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
import com.example.canoeworld.objects.Equipment

const val TAG1 = "EquipmentAdapter"

class EquipmentAdapter(private val values: List<Equipment>)
                      : RecyclerView.Adapter<EquipmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG1, "viewHolder created")
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_equipment,
                parent,
                false
            )
        )

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.mName.text = item.getName()
        holder.mSPrice.text = "kajak jednoosoby: ${item.getSinglePrice()}zł"
        holder.mSDouble.text = "kajak dwuosoby: ${item.getDoublePrice()}zł"
        holder.mAddress.text = "Adres: ${item.getAddress()}"
        holder.mPhone.text = item.getTelephone()
        Linkify.addLinks(holder.mPhone, Linkify.PHONE_NUMBERS)
        Log.d(TAG1, "viewHolder bind!")
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: View) :
        RecyclerView.ViewHolder(binding) {
        val mName    = binding.findViewById<TextView>(R.id.equipName)!!
        val mSPrice  = binding.findViewById<TextView>(R.id.equipSingle)!!
        val mSDouble = binding.findViewById<TextView>(R.id.equipDouble)!!
        val mAddress = binding.findViewById<TextView>(R.id.eqipAddr)!!
        val mPhone   = binding.findViewById<TextView>(R.id.equipPhone)!!

    }

}