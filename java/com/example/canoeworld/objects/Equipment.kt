package com.example.canoeworld.objects

import android.os.Parcel
import android.os.Parcelable

class Equipment() : Parcelable{
    private var lakeId      : Int?    = null
    private var name        : String? = null
    private var singlePrice : Float? = null
    private var doublePrice : Float?  = null
    private var address     : String? = null
    private var telephone   : String? = null

    constructor(parcel: Parcel) : this() {
        lakeId = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        singlePrice = parcel.readValue(Float::class.java.classLoader) as? Float
        doublePrice = parcel.readValue(Float::class.java.classLoader) as? Float
        address = parcel.readString()
        telephone = parcel.readString()
    }


    fun getId()           : Int    { return lakeId!! }
    fun getName()         : String { return name!!}
    fun getSinglePrice()  : Float  { return singlePrice!! }
    fun getDoublePrice()  : Float  { return doublePrice!! }
    fun getAddress()      : String { return address!! }
    fun getTelephone()    : String { return telephone!! }


    fun setId(ld_id : Int)          { this.lakeId = ld_id }
    fun setName(name : String)      { this.name = name}
    fun setSinglePrice(sing: Float) { this.singlePrice = sing }
    fun setDoublePrice(doub: Float) { this.doublePrice = doub }
    fun setAddress(addr : String)   { this.address = addr }
    fun setTelephone(tele : String) { this.telephone = tele }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(lakeId)
        parcel.writeString(name)
        parcel.writeValue(singlePrice)
        parcel.writeValue(doublePrice)
        parcel.writeString(address)
        parcel.writeString(telephone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Equipment> {
        override fun createFromParcel(parcel: Parcel): Equipment {
            return Equipment(parcel)
        }

        override fun newArray(size: Int): Array<Equipment?> {
            return arrayOfNulls(size)
        }
    }
}