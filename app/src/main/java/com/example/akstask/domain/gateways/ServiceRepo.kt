package com.example.akstask.domain.gateways

import com.example.akstask.domain.entities.ProductsRes
import io.reactivex.Observable

object ServiceRepo {

    fun getProducts(): Observable<ProductsRes> {
        return RetrofitClient.instance.getAllProducts()
    }

}