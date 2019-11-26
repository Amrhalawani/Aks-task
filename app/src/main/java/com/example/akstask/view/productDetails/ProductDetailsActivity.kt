package com.example.akstask.view.productDetails

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.akstask.R
import com.example.akstask.domain.entities.ProductItem
import com.example.akstask.view.util.Constants
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.activity_product_details.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*
import android.os.Build
import android.content.Intent
import android.net.Uri


class ProductDetailsActivity : AppCompatActivity() {

    private var mProductPicsFragmentsPagerAdapter: PicsPagerAdapter? = null

    lateinit var product: ProductItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        product = intent.getParcelableExtra(Constants.PRODUCT_OBJ_KEY)

        setupAppbar()
        setupProductPics()
        updateViews()

    }

    private fun updateViews() {
        text_title_toolbar__products_detail.text = product.name
        text_date.text = formatDate(product.date)
        text_product_name.text = product.name
        text_product_desc.text = product.description

        updateWorkLevelsView()

        image_google_play.setOnClickListener { val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(
                "https://play.google.com/store/apps/details?id=com.tos.tasbih_arabic")
            setPackage("com.android.vending")
        }
            startActivity(intent) }
        image_app_store.setOnClickListener { openinBrowser(product.appStoreLink!!) }
        image_web_link.setOnClickListener { openinBrowser(product.webLink!!) }

    }

    private fun formatDate(date: String?): String {
        return try {
            val date1: Date = SimpleDateFormat("yyyy-MM-dd").parse(date)

            val formatter1 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                SimpleDateFormat("EEEE d MMMM yyyy", Locale.forLanguageTag("ar-SA-nu-arab"))
            } else {
                SimpleDateFormat("dd MMMM, yyyy")
            }
            val date: String = formatter1.format(date1)
            date
        } catch (e: Exception) {
            ""
        }
    }

    private fun updateWorkLevelsView() {
        var result = ""
        Observable.fromIterable(product.workLevels)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { i -> result += "- ${i.text} \n \n" }
                , { error -> }
                , {
                    text_work_level.text = result
                })


    }

    private fun setupProductPics() {
        mProductPicsFragmentsPagerAdapter = PicsPagerAdapter(supportFragmentManager)
        vp_product_pics.adapter = mProductPicsFragmentsPagerAdapter
        val dotsIndicator = findViewById<WormDotsIndicator>(R.id.dots_indicator_product)
        dotsIndicator.setViewPager(vp_product_pics)
    }


    private fun setupAppbar() {
        setSupportActionBar(toolbar__products_detail)
        supportActionBar?.elevation = 8.0f
        image_back_products_detail.setOnClickListener { finish() }
        image_share__products_detail.setOnClickListener {

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "${product.name} : ${product.description} / ${product.googlePlayLink}")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    fun openinBrowser(url:String){

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }


}