package com.example.canoeworld.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canoeworld.R
import com.example.canoeworld.adapters.AccommodationAdapter
import com.example.canoeworld.objects.Accomodation

private const val ACCOMMODATION = "Accommodation"
private const val TAG1 = "AccommodationFragment"

/**
 * A fragment representing a list of Accommodation.
 */
class AccommodationFragment : Fragment() {

    private var          columnCount = 1
    private var          accommodation : ArrayList<Accomodation>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            accommodation = it.getParcelableArrayList(ACCOMMODATION)
        }
        Log.d(TAG1, "onCreate args: $arguments")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_accommodation_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = AccommodationAdapter(accommodation!!)
            }
        }
        Log.d(TAG1, "view created!")
        return view
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount : Int) =
            AccommodationFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                    putParcelableArrayList(ACCOMMODATION, accommodation)
                }
            }
    }
}