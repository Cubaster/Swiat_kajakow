package com.example.canoeworld.objects

import com.example.canoeworld.R
import java.util.ArrayList

object PlaceholderContent {

    /**
     * An array of Lake district items.
     */
    val ITEMS: MutableList<PlaceholderItem> = ArrayList()
    private const val COUNT = 6

    init {
        // Adding LakeDistricts
        addItem(createPlaceholderItem("Pojezierze Zachodniopomorskie", R.mipmap.pojezierze_zachodniopomorskie_foreground))
        addItem(createPlaceholderItem("Pojezierze Wschodniopomorskie", R.mipmap.pojezierze_wschodniopomorskie_foreground))
        addItem(createPlaceholderItem("Pojezierze Południowopomorskie", R.mipmap.pojezierze_poludniowopomorskie_foreground))
        addItem(createPlaceholderItem("Pojezierze Wielkopolskie", R.mipmap.pojezierze_wielkopolskie_foreground))
        addItem(createPlaceholderItem("Pojezierze Chełmińsko-Dobrzyńskie", R.mipmap.pojezierze_chelminsko_dobrzynskie_foreground))
        addItem(createPlaceholderItem("Pojezierze Mazurskie", R.mipmap.pojezierze_mazurskie_foreground))
    }

    private fun addItem(item: PlaceholderItem) {
        ITEMS.add(item)
    }

    private fun createPlaceholderItem(title: String, img: Int): PlaceholderItem {
        return PlaceholderItem(title, img)
    }

    data class PlaceholderItem(val title: String, val image: Int)
}