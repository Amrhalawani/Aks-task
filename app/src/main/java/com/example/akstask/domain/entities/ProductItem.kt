package com.example.akstask.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ProductItem(@field:SerializedName("date")
    val date: String? = null, @field:SerializedName("app_store_link")
    val appStoreLink: String? = null, @field:SerializedName("name")
    val name: String? = null, @field:SerializedName("description")
    val description: String? = null, @field:SerializedName("google_play_link")
    val googlePlayLink: String? = null, @field:SerializedName("id")
    val id: Int? = null, @field:SerializedName("web_link")
    val webLink: String? = null, @field:SerializedName("work_levels")
    val workLevels: @RawValue List<WorkLevelsItem>? = null) : Parcelable {
}

@Parcelize
data class WorkLevelsItem(

    @field:SerializedName("text")
    val text: String? = null
):Parcelable
