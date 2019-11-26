package com.example.akstask.view.productDetails


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.akstask.R
import com.example.akstask.view.util.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_product_pics.view.*


class ProductPicFragment : Fragment() {

    companion object {
        fun newInstance(): ProductPicFragment {
            val fragment = ProductPicFragment()
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_product_pics, container, false)

        Picasso.get().load(Utils.getRandomPic()).into(view.image_product_frag)

        return view
    }



}
