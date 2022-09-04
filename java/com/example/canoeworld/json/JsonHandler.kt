package com.example.canoeworld.json

import android.content.Context
import android.util.Log
import com.example.canoeworld.database.DBHelper
import com.example.canoeworld.objects.Accomodation
import com.example.canoeworld.objects.Equipment
import com.example.canoeworld.objects.LakeItem
import com.example.canoeworld.objects.Route
import org.json.JSONArray
import org.json.JSONTokener
import java.lang.Exception

const val TAG = "Json"
class JsonHandler(private val database: DBHelper, private val context: Context) {
    fun loadItems(){
        loadLakes()
        loadRoutes()
        loadAccommodation()
        loadEquipment()
        Log.d(TAG, "modules loaded")
    }

    private fun getJsonFromAssets(fileName : String): String?{
        val jsonString: String = try {
                val inputStream = context.assets.open(fileName)
                val size        = inputStream.available()
                val buffer      = ByteArray(size)

                inputStream.read(buffer)
                inputStream.close()
                buffer.toString(charset("UTF-8"))
            }
            catch (e: Exception){
                e.printStackTrace()
                return null
            }
        Log.d(TAG, "$fileName loaded")
        return jsonString
    }

    private fun loadLakes(){
        val response = getJsonFromAssets("lakeDistrict.json")
        if(response != null)
        {
            val jsonArray = JSONTokener(response).nextValue() as JSONArray
            for (i in 0 until jsonArray.length())
            {
                val id    = jsonArray.getJSONObject(i).getInt("id")
                val name  = jsonArray.getJSONObject(i).getString("name")
                val image = jsonArray.getJSONObject(i).getString("image")
                val desc  = jsonArray.getJSONObject(i).getString("desc")
                val lake = LakeItem

                lake.setId(id)
                lake.setName(name)
                lake.setImageId(image)
                lake.setDescription(desc)

                database.addLakeDistrict(lake)
            }
            Log.d(TAG, "lakes loaded")
        }
    }

    private fun loadRoutes()
    {
        val response = getJsonFromAssets("routes.json")
        if(response != null)
        {
            val jsonArray = JSONTokener(response).nextValue() as JSONArray
            for (i in 0 until jsonArray.length())
            {
                val id    = jsonArray.getJSONObject(i).getInt("ld_id")
                val name  = jsonArray.getJSONObject(i).getString("name")
                val dist  = jsonArray.getJSONObject(i).getDouble("dist").toFloat()
                val diff  = jsonArray.getJSONObject(i).getString("diff")
                val image = jsonArray.getJSONObject(i).getString("image")
                val desc  = jsonArray.getJSONObject(i).getString("desc")
                val lat   = jsonArray.getJSONObject(i).getDouble("lat").toFloat()
                val longi = jsonArray.getJSONObject(i).getDouble("long").toFloat()
                val route = Route()

                route.setId(id)
                route.setName(name)
                route.setDistance(dist)
                route.setDifficulty(diff)
                route.setImage(image)
                route.setDescription(desc)
                route.setLatitude(lat)
                route.setLongitude(longi)

                database.addRoute(route)
            }
            Log.d(TAG, "routes loaded")
        }
    }

    private fun loadAccommodation()
    {
        val response = getJsonFromAssets("accommodation.json")
        if(response != null)
        {
            val jsonArray = JSONTokener(response).nextValue() as JSONArray
            for (i in 0 until jsonArray.length())
            {
                val id     = jsonArray.getJSONObject(i).getInt("ld_id")
                val name   = jsonArray.getJSONObject(i).getString("name")
                val rating = jsonArray.getJSONObject(i).getDouble("rating").toFloat()
                val route  = jsonArray.getJSONObject(i).getString("route")
                val url    = jsonArray.getJSONObject(i).getString("url")
                val accom  = Accomodation()

                accom.setId(id)
                accom.setName(name)
                accom.setRating(rating)
                accom.setRoute(route)
                accom.setUrl(url)

                database.addAccommodation(accom)
            }
            Log.d(TAG, "accommodation loaded")
        }
    }

    private fun loadEquipment()
    {
        val response = getJsonFromAssets("equipment.json")
        if(response != null)
        {
            val jsonArray = JSONTokener(response).nextValue() as JSONArray
            for (i in 0 until jsonArray.length())
            {
                val id          = jsonArray.getJSONObject(i).getInt("ld_id")
                val name        = jsonArray.getJSONObject(i).getString("name")
                val singlePrice = jsonArray.getJSONObject(i).getDouble("single").toFloat()
                val doublePrice = jsonArray.getJSONObject(i).getDouble("double").toFloat()
                val address     = jsonArray.getJSONObject(i).getString("address")
                val telephone   = jsonArray.getJSONObject(i).getString("telephone")
                val equip  = Equipment()

                equip.setId(id)
                equip.setName(name)
                equip.setSinglePrice(singlePrice)
                equip.setDoublePrice(doublePrice)
                equip.setAddress(address)
                equip.setTelephone(telephone)

                database.addEquipment(equip)
            }
            Log.d(TAG, "equipment loaded")
        }
    }



}