package com.example.akstask.view.util

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.example.akstask.domain.entities.ProductItem
import com.example.akstask.view.productDetails.ProductDetailsActivity
import com.example.akstask.view.util.Constants.PRODUCT_OBJ_KEY

object ActivityLauncher{


    fun toProductDetail(context: Context,prod:ProductItem){
        val i = Intent(context,ProductDetailsActivity::class.java)
        i.putExtra(PRODUCT_OBJ_KEY,prod)
        startActivity(context,i,null)
    }
}