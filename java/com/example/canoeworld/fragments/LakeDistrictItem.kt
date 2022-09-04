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
import com.example.canoeworld.adapters.LakeDistrictAdapter
import com.example.canoeworld.R
import com.example.canoeworld.objects.PlaceholderContent

private const val TAG4 = "LakeFragment"

/**
 * A fragment representing a list of Items.
 */
class LakeDistrictItem : Fragment() {

    private var columnCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
        Log.d(TAG4, "onCreate args: $arguments")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lake_district_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = LakeDistrictAdapter(PlaceholderContent.ITEMS, context)
            }
        }
        Log.d(TAG4, "view created!")
        return view
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        fun newInstance(columnCount: Int) =
            LakeDistrictItem().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}