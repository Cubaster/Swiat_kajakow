package com.example.canoeworld.objects

import android.os.Parcel
import android.os.Parcelable

class Accomodation() : Parcelable {
    private var lakeId : Int? = null
    private var name : String? = null
    private var rating : Float? = null
    private var route : String? = null
    private var url : String? = null

    constructor(parcel: Parcel) : this() {
        lakeId = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        rating = parcel.readValue(Float::class.java.classLoader) as? Float
        route = parcel.readString()
        url = parcel.readString()
    }

    fun getId(): Int {return lakeId!! }
    fun getName(): String {return name!!}
    fun getRating(): Float {return rating!!}
    fun getRoute(): String {return route!!}
    fun getUrl(): String {return url!!}

    fun setId(ld_id : Int) {this.lakeId = ld_id}
    fun setName(name: String) {this.name = name}
    fun setRating(rate: Float){this.rating = rate}
    fun setRoute(route : String) {this.route = route}
    fun setUrl(url : String) {this.url = url}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(lakeId)
        parcel.writeString(name)
        parcel.writeValue(rating)
        parcel.writeString(route)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Accomodation> {
        override fun createFromParcel(parcel: Parcel): Accomodation {
            return Accomodation(parcel)
        }

        override fun newArray(size: Int): Array<Accomodation?> {
            return arrayOfNulls(size)
        }
    }
}