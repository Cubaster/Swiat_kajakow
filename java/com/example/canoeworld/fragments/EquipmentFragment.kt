package com.example.canoeworld.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.canoeworld.R
import com.example.canoeworld.adapters.EquipmentAdapter
import com.example.canoeworld.objects.Equipment

const val EQUIPMENTS = "Equipment"
private const val TAG2 = "EquipmentFragment"

/**
 * A fragment representing a list of Items.
 */
class EquipmentFragment : Fragment() {

    private var columnCount = 1
    private var equipment : ArrayList<Equipment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            equipment   = it.getParcelableArrayList(EQUIPMENTS)
        }
        Log.d(TAG2, "onCreate args: $arguments")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_equipment_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = EquipmentAdapter(equipment!!)
            }
        }
        Log.d(TAG2, "view created!")
        return view
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            EquipmentFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                    putParcelableArrayList(EQUIPMENTS, equipment)
                }
            }
    }
}