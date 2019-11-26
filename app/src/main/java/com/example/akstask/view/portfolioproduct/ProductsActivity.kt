package com.example.akstask.view.portfolioproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.akstask.R
import com.example.akstask.domain.entities.MainProductItem
import com.example.akstask.domain.entities.ProductsRes
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_products.*

import android.os.Handler
import com.example.akstask.view.util.ActivityLauncher
import com.example.akstask.view.util.Utils


class ProductsActivity : AppCompatActivity() {
    var productsAdaptor: ProductsAdaptor? = null
    lateinit var tabsView: View
    lateinit var mProductsList: List<MainProductItem>

    var currentTabSelectedPos = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        setupActionBar()
        setupProductRV()
        setupTabLayout()
        loadProducts()
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar__products_act)
        supportActionBar?.elevation = 8.0f

    }

    private fun loadProducts() {

        val model = ViewModelProviders.of(this).get(ProductsVM::class.java)

        model.getProducts(this).observe(this, Observer<ProductsRes> { result ->
            progress_main.visibility = View.GONE
            mProductsList = result.data!!
            if (mProductsList.isNotEmpty()) {
                addTabs(mProductsList)
                productsAdaptor?.updateList(result.data[currentTabSelectedPos].products)
                layout_empty_view.visibility = View.GONE
            } else {
                layout_empty_view.visibility = View.VISIBLE
            }


        })
    }

    private fun setupProductRV() {

        rv_products__product_act.layoutManager = GridLayoutManager(this,
            Utils.numberOfColumns(this)
        )

        productsAdaptor = ProductsAdaptor(this) { prod ->
            ActivityLauncher.toProductDetail(this,prod)
        }

        rv_products__product_act.adapter = productsAdaptor

    }

    private fun setupTabLayout() {
        tabsView = tablayout_categories__product_act

        onCategoriesTabSelected()
    }

    private fun addTabs(mainProductlist: List<MainProductItem?>) {
        if ((tabsView as TabLayout).tabCount <= 0) {
            for (c in 0 until mainProductlist.size) {
                (tabsView as TabLayout).addTab((tabsView as TabLayout).newTab().setText(mainProductlist[c]?.name!!))
            }
        }

        //work around sol for rtl gravity issue
        Handler().postDelayed({
            tabsView.scrollX = tabsView.width
            (tabsView as TabLayout).getTabAt(0)?.select()
        }, 50)
    }

    private fun onCategoriesTabSelected() {

        tablayout_categories__product_act.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                currentTabSelectedPos = tab.position
                if (mProductsList[currentTabSelectedPos].products!!.isNotEmpty()) {
                    productsAdaptor?.updateList(mProductsList[currentTabSelectedPos].products)
                    layout_empty_view.visibility = View.GONE
                    rv_products__product_act.visibility=View.VISIBLE
                } else {
                    layout_empty_view.visibility = View.VISIBLE
                    rv_products__product_act.visibility=View.GONE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}
