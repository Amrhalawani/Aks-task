package com.example.akstask.domain.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ProductsRes(

	@field:SerializedName("Errors")
	val errors: List<Any?>? = null,

	@field:SerializedName("Status_code")
	val statusCode: Int? = null,

	@field:SerializedName("Data")
	val data: List<MainProductItem>? = null
)



data class MainProductItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("products")
    val products: List<ProductItem>? = null
)
