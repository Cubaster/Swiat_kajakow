package com.example.canoeworld.objects

import android.os.Parcel
import android.os.Parcelable


class Route : Parcelable{
    private var lakeId      : Int?    = null
    private var name        : String? = null
    private var distance    : Float?  = null
    private var difficulty  : String? = null
    private var image       : String? = null
    private var description : String? = null
    private var latitude    : Float?  = null
    private var longitude   : Float?  = null


    fun getId()          : Int    { return lakeId!! }
    fun getName()        : String { return name!! }
    fun getDistance()    : Float  { return distance!! }
    fun getDifficulty()  : String { return difficulty!! }
    fun getImage()       : String { return image!! }
    fun getDescription() : String { return description!! }
    fun getLatitude()    : Float  { return latitude!!}
    fun getLongitude()   : Float  { return longitude!!}

    fun setId(ld_id : Int)            { this.lakeId = ld_id }
    fun setName(name: String)         { this.name = name }
    fun setDistance(dist: Float)      { this.distance = dist }
    fun setDifficulty(diff : String)  { this.difficulty = diff }
    fun setImage(img : String)        { this.image = img }
    fun setDescription(desc : String) { this.description = desc }
    fun setLatitude(lat : Float)      { this.latitude = lat}
    fun setLongitude(longi : Float)   { this.longitude = longi}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(lakeId!!)
        parcel.writeString(name!!)
        parcel.writeFloat(distance!!)
        parcel.writeString(difficulty!!)
        parcel.writeString(image!!)
        parcel.writeString(description!!)
        parcel.writeFloat(latitude!!)
        parcel.writeFloat(longitude!!)
    }

    @JvmField
    val CREATOR: Parcelable.Creator<*> = object : Parcelable.Creator<Any?> {
        override fun createFromParcel(`in`: Parcel): Route {
            return this@Route
        }

        override fun newArray(size: Int): Array<LakeItem?> {
            return arrayOfNulls(size)
        }
    }
}