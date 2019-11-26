package com.example.akstask.domain.gateways

import com.example.akstask.domain.entities.ProductsRes
import io.reactivex.Observable
import retrofit2.http.*


interface ApiService {


    // --------------------- All Products -----------------------------

    @GET("products/all")
    fun getAllProducts(): Observable<ProductsRes>



}