package com.example.canoeworld.objects

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator


object LakeItem : Parcelable{

    private var regionId          : Int?    = null
    private var regionName        : String? = null
    private var regionImageId     : String? = null
    private var regionDescription : String? = null

    fun getId():          Int    { return regionId!! }
    fun getName():        String { return regionName!! }
    fun getImageId():     String { return regionImageId!! }
    fun getDescription(): String { return regionDescription!! }

    fun setId(id: Int)               { this.regionId = id }
    fun setName(name : String)       { this.regionName = name }
    fun setImageId(imgId: String)    { this.regionImageId = imgId }
    fun setDescription(desc: String) { this.regionDescription = desc }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(regionId!!)
        parcel.writeString(regionName)
        parcel.writeString(regionImageId)
        parcel.writeString(regionDescription)

    }

    @JvmField
    val CREATOR: Creator<*> = object : Creator<Any?> {
        override fun createFromParcel(`in`: Parcel): LakeItem {
            return this@LakeItem
        }

        override fun newArray(size: Int): Array<LakeItem?> {
            return arrayOfNulls(size)
        }
    }


}