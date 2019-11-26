package com.example.akstask.view.portfolioproduct

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.akstask.domain.entities.ProductsRes
import com.example.akstask.domain.gateways.ServiceRepo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductsVM : ViewModel() {

    private val disposables = CompositeDisposable()

    fun getProducts(context: Context): LiveData<ProductsRes> {
        val data = MutableLiveData<ProductsRes>()
        disposables.add(
            ServiceRepo.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value = it
                }, { throwable ->
throwable.printStackTrace()
                    Toast.makeText(context, "${throwable.message}", Toast.LENGTH_SHORT).show()
                })
        )
        return data
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}
