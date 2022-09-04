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
import com.example.canoeworld.adapters.RouteAdapter
import com.example.canoeworld.objects.Route

const val ROUTES = "Routes"
private const val TAG6 = "RouteFragment"
/**
 * A fragment representing a list of Routes.
 */
class RoutesFragment : Fragment() {

    private var          columnCount = 1
    private var          routes   : ArrayList<Route>? = null
    private lateinit var mAdapter : RouteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            routes = it.getParcelableArrayList(ROUTES)
        }
        Log.d(TAG6, "onCreate args: $arguments")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_routes_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = RouteAdapter(routes!!, context)
                mAdapter = adapter as RouteAdapter
            }
        }
        Log.d(TAG6, "view created!")
        return view
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            RoutesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                    putParcelableArrayList(ROUTES, routes)
                }
            }
    }
}