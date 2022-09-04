package com.example.canoeworld.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.canoeworld.objects.Accomodation
import com.example.canoeworld.objects.Equipment
import com.example.canoeworld.objects.LakeItem
import com.example.canoeworld.objects.Route

const val TAG = "Database"

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val  DATABASE_NAME = "canoe.db"
        private const val DATABASE_VERSION = 2
    }

    object LakeDistrict {
        const val TABLE_NAME    : String = "lakeDistrict"
        const val LAKE_ID       : String = "Id"
        const val LAKE_NAME     : String = "Name"
        const val LAKE_IMAGE_ID : String = "Image_id"
        const val LAKE_DESC     : String = "Description"
    }

    object RouteDb {
        const val TABLE_NAME     : String = "Routes"
        const val ROUTE_SELF_ID  : String = "SelfId"
        const val ROUTE_ID       : String = "Id"
        const val ROUTE_NAME     : String = "Name"
        const val ROUTE_DIST     : String = "Distance"
        const val ROUTE_DIFF     : String = "Difficulty"
        const val ROUTE_IMAGE_ID : String = "Image_id"
        const val ROUTE_DESC     : String = "Description"
        const val ROUTE_LAT      : String = "Latitude"
        const val ROUTE_LONG     : String = "Longitude"
    }

    object AccommodateDb {
        const val TABLE_NAME    : String = "Accommodation"
        const val ACCOM_SELF_ID : String = "SelfId"
        const val ACCOM_ID      : String = "Id"
        const val ACCOM_NAME    : String = "Name"
        const val ACCOM_RATING  : String = "Rating"
        const val ACCOM_ROUTE   : String = "Route"
        const val ACCOM_URL     : String = "Url"
    }

    object EquipmentDb {
        const val TABLE_NAME    : String = "Equipment"
        const val EQUIP_SELF_ID : String = "SelfId"
        const val EQUIP_ID      : String = "Id"
        const val EQUIP_NAME    : String = "Name"
        const val EQUIP_SINGLE  : String = "single"
        const val EQUIP_DOUBLE  : String = "tandem"
        const val EQUIP_ADDRESS : String = "address"
        const val EQUIP_PHONE   : String = "telephone"
    }


    override fun onCreate(database: SQLiteDatabase) {
        val lakeQuery : String =
            ("CREATE TABLE IF NOT EXISTS ${LakeDistrict.TABLE_NAME} (${LakeDistrict.LAKE_ID} NUMBER PRIMARY KEY," +
                    "                                                ${LakeDistrict.LAKE_NAME} TEXT," +
                    "                                                ${LakeDistrict.LAKE_IMAGE_ID}  TEXT," +
                    "                                                ${LakeDistrict.LAKE_DESC} TEXT)")
        Log.d(TAG, "lake query made")
        val routeQuery :String =
            ("CREATE TABLE IF NOT EXISTS ${RouteDb.TABLE_NAME} (${RouteDb.ROUTE_SELF_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                               "${RouteDb.ROUTE_ID} INTEGER," +
                                                               "${RouteDb.ROUTE_NAME} TEXT," +
                                                               "${RouteDb.ROUTE_DIST} REAL," +
                                                               "${RouteDb.ROUTE_DIFF} TEXT," +
                                                               "${RouteDb.ROUTE_IMAGE_ID} TEXT, " +
                                                               "${RouteDb.ROUTE_DESC} TEXT," +
                                                               "${RouteDb.ROUTE_LAT} REAL," +
                                                               "${RouteDb.ROUTE_LONG} REAL)")
        Log.d(TAG, "route query made")
        val accommodationQuery : String =
            ("CREATE TABLE IF NOT EXISTS ${AccommodateDb.TABLE_NAME}(${AccommodateDb.ACCOM_SELF_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                                    "${AccommodateDb.ACCOM_ID} INTEGER," +
                                                                    "${AccommodateDb.ACCOM_NAME} TEXT," +
                                                                    "${AccommodateDb.ACCOM_RATING} REAL," +
                                                                    "${AccommodateDb.ACCOM_ROUTE} TEXT," +
                                                                    "${AccommodateDb.ACCOM_URL} TEXT)")
        Log.d(TAG, "accom query made")
        val equipmentQuery : String =
            ("CREATE TABLE IF NOT EXISTS ${EquipmentDb.TABLE_NAME} (${EquipmentDb.EQUIP_SELF_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                                   "${EquipmentDb.EQUIP_ID} INTEGER," +
                                                                   "${EquipmentDb.EQUIP_NAME} TEXT," +
                                                                   "${EquipmentDb.EQUIP_SINGLE} REAL," +
                                                                   "${EquipmentDb.EQUIP_DOUBLE} REAL," +
                                                                   "${EquipmentDb.EQUIP_ADDRESS} TEXT," +
                                                                   "${EquipmentDb.EQUIP_PHONE} TEXT)")
        Log.d(TAG, "equip query made")

        database.execSQL(lakeQuery)
        database.execSQL(routeQuery)
        database.execSQL(accommodationQuery)
        database.execSQL(equipmentQuery)
        Log.d(TAG, "queries executed")
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        database.execSQL("DROP TABLE IF EXISTS ${LakeDistrict.TABLE_NAME}")
        database.execSQL("DROP TABLE IF EXISTS ${RouteDb.TABLE_NAME}")
        database.execSQL("DROP TABLE IF EXISTS ${AccommodateDb.TABLE_NAME}")
        database.execSQL("DROP TABLE IF EXISTS ${EquipmentDb.TABLE_NAME}")
        Log.d(TAG, "Database upgraded")
        onCreate(database)
    }

    fun addLakeDistrict(lakeDistrict: LakeItem){
        val database = this.writableDatabase
        val values = ContentValues()
        values.put(LakeDistrict.LAKE_ID, lakeDistrict.getId())
        values.put(LakeDistrict.LAKE_NAME, lakeDistrict.getName())
        values.put(LakeDistrict.LAKE_IMAGE_ID, lakeDistrict.getImageId())
        values.put(LakeDistrict.LAKE_DESC, lakeDistrict.getDescription())

        database.insert(LakeDistrict.TABLE_NAME, null, values)
        database.close()
        Log.d(TAG, "Lakes to db")
    }

    @SuppressLint("Range")
    fun getLakeDistrict(position: Int): ArrayList<LakeItem>{
        val lakeDistricts = ArrayList<LakeItem>()
        val query =
            "SELECT * FROM ${LakeDistrict.TABLE_NAME} WHERE ${LakeDistrict.LAKE_ID} = $position"
        val database = writableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do{
                val lakeDistrict = LakeItem
                lakeDistrict.setId(cursor.getInt(cursor.getColumnIndex(LakeDistrict.LAKE_ID)))
                lakeDistrict.setName(cursor.getString(cursor.getColumnIndex(LakeDistrict.LAKE_NAME)))
                lakeDistrict.setImageId(cursor.getString(cursor.getColumnIndex(LakeDistrict.LAKE_IMAGE_ID)))
                lakeDistrict.setDescription(cursor.getString(cursor.getColumnIndex(LakeDistrict.LAKE_DESC)))
                lakeDistricts.add(lakeDistrict)
            }while (cursor.moveToNext())
        }
        cursor.close()
        database.close()
        Log.d(TAG, "Lakes from db")
        return lakeDistricts
    }

    fun addRoute(route: Route){
        val database = this.writableDatabase
        val values = ContentValues()
        values.put(RouteDb.ROUTE_ID, route.getId())
        values.put(RouteDb.ROUTE_NAME, route.getName())
        values.put(RouteDb.ROUTE_DIST, route.getDistance())
        values.put(RouteDb.ROUTE_DIFF, route.getDifficulty())
        values.put(RouteDb.ROUTE_IMAGE_ID, route.getImage())
        values.put(RouteDb.ROUTE_DESC, route.getDescription())
        values.put(RouteDb.ROUTE_LAT, route.getLatitude())
        values.put(RouteDb.ROUTE_LONG, route.getLongitude())

        database.insert(RouteDb.TABLE_NAME, null, values)
        database.close()
        Log.d(TAG, "Routes to db")
    }

    @SuppressLint("Range")
    fun getRoutes(position: Int): ArrayList<Route>{
        val routes = ArrayList<Route>()
        val query =
            "SELECT * FROM ${RouteDb.TABLE_NAME} WHERE ${RouteDb.ROUTE_ID} = $position"
        val database = writableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do{
                val route = Route()
                route.setId(cursor.getInt(cursor.getColumnIndex(RouteDb.ROUTE_ID)))
                route.setName(cursor.getString(cursor.getColumnIndex(RouteDb.ROUTE_NAME)))
                route.setDistance(cursor.getFloat(cursor.getColumnIndex(RouteDb.ROUTE_DIST)))
                route.setDifficulty(cursor.getString(cursor.getColumnIndex(RouteDb.ROUTE_DIFF)))
                route.setImage(cursor.getString(cursor.getColumnIndex(RouteDb.ROUTE_IMAGE_ID)))
                route.setDescription(cursor.getString(cursor.getColumnIndex(RouteDb.ROUTE_DESC)))
                route.setLatitude(cursor.getFloat(cursor.getColumnIndex(RouteDb.ROUTE_LAT)))
                route.setLongitude(cursor.getFloat(cursor.getColumnIndex(RouteDb.ROUTE_LONG)))
                routes.add(route)
            }while (cursor.moveToNext())
        }
        cursor.close()
        database.close()
        Log.d(TAG, "Routes from db")
        return routes
    }

    fun addAccommodation(accom: Accomodation){
        val database = this.writableDatabase
        val values = ContentValues()
        values.put(AccommodateDb.ACCOM_ID, accom.getId())
        values.put(AccommodateDb.ACCOM_NAME, accom.getName())
        values.put(AccommodateDb.ACCOM_RATING, accom.getRating())
        values.put(AccommodateDb.ACCOM_ROUTE, accom.getRoute())
        values.put(AccommodateDb.ACCOM_URL, accom.getUrl())

        database.insert(AccommodateDb.TABLE_NAME, null, values)
        database.close()
        Log.d(TAG, "Accommodation to db")
    }

    @SuppressLint("Range")
    fun getAccommodation(position: Int): ArrayList<Accomodation>{
        val accom = ArrayList<Accomodation>()
        val query =
            "SELECT * FROM ${AccommodateDb.TABLE_NAME} WHERE ${AccommodateDb.ACCOM_ID}=$position"
        val database = writableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do{
                val acc = Accomodation()
                acc.setId(cursor.getInt(cursor.getColumnIndex(AccommodateDb.ACCOM_ID)))
                acc.setName(cursor.getString(cursor.getColumnIndex(AccommodateDb.ACCOM_NAME)))
                acc.setRating(cursor.getFloat(cursor.getColumnIndex(AccommodateDb.ACCOM_RATING)))
                acc.setRoute(cursor.getString(cursor.getColumnIndex(AccommodateDb.ACCOM_ROUTE)))
                acc.setUrl(cursor.getString(cursor.getColumnIndex(AccommodateDb.ACCOM_URL)))
                accom.add(acc)
            }while (cursor.moveToNext())
        }
        cursor.close()
        database.close()
        Log.d(TAG, "Accommodation from db")
        return accom
    }

    fun addEquipment(equip: Equipment){
        val database = this.writableDatabase
        val values = ContentValues()
        values.put(EquipmentDb.EQUIP_ID, equip.getId())
        values.put(EquipmentDb.EQUIP_NAME, equip.getName())
        values.put(EquipmentDb.EQUIP_SINGLE, equip.getSinglePrice())
        values.put(EquipmentDb.EQUIP_DOUBLE, equip.getDoublePrice())
        values.put(EquipmentDb.EQUIP_ADDRESS, equip.getAddress())
        values.put(EquipmentDb.EQUIP_PHONE, equip.getTelephone())

        database.insert(EquipmentDb.TABLE_NAME, null, values)
        database.close()
        Log.d(TAG, "Equipment to db")
    }

    @SuppressLint("Range")
    fun getEquipment(position: Int): ArrayList<Equipment>{
        val equips = ArrayList<Equipment>()
        val query =
            "SELECT * FROM ${EquipmentDb.TABLE_NAME} WHERE ${EquipmentDb.EQUIP_ID}=$position"
        val database = writableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do{
                val equip = Equipment()
                equip.setId(cursor.getInt(cursor.getColumnIndex(EquipmentDb.EQUIP_ID)))
                equip.setName(cursor.getString(cursor.getColumnIndex(EquipmentDb.EQUIP_NAME)))
                equip.setSinglePrice(cursor.getFloat(cursor.getColumnIndex(EquipmentDb.EQUIP_SINGLE)))
                equip.setDoublePrice(cursor.getFloat(cursor.getColumnIndex(EquipmentDb.EQUIP_DOUBLE)))
                equip.setAddress(cursor.getString(cursor.getColumnIndex(EquipmentDb.EQUIP_ADDRESS)))
                equip.setTelephone(cursor.getString(cursor.getColumnIndex(EquipmentDb.EQUIP_PHONE)))
                equips.add(equip)
            }while (cursor.moveToNext())
        }
        cursor.close()
        database.close()
        Log.d(TAG, "Equipment from db")
        return equips
    }
}