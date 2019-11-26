package com.example.akstask.view.portfolioproduct

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.akstask.R
import com.example.akstask.domain.entities.ProductItem
import com.example.akstask.view.util.Utils
import kotlinx.android.synthetic.main.item_product.view.*


/**
 * Created by Amr hal on 26/11/2019.
 */

class ProductsAdaptor(var context: Context, val listener: (ProductItem) -> Unit) :
    RecyclerView.Adapter<ProductViewHolder>() {
    var mList: List<ProductItem>? = arrayListOf()

    fun updateList(newitems: List<ProductItem>?) {
        mList = newitems
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_product,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(mList?.get(position)!!, listener)

    }

    override fun getItemCount(): Int {
        return mList!!.size
    }}
class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(item: ProductItem, listener: (ProductItem) -> Unit) = with(itemView) {

        itemView.image__pi.setImageResource(Utils.getRandomPic())
        itemView.text_product_title__pi.text = item.name
            setOnClickListener {
                listener(item)

            }


    }
}
