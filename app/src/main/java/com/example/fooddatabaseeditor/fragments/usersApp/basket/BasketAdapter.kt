package com.example.fooddatabaseeditor.fragments.usersApp.basket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddatabaseeditor.R
import com.example.fooddatabaseeditor.fragments.databaseManager.list.ListFragmentDirections
import com.example.fooddatabaseeditor.model.Product
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.order_row.view.*
import kotlinx.android.synthetic.main.product_row.view.*

class BasketAdapter: RecyclerView.Adapter<BasketAdapter.ViewHolder>() {
    private var productList = emptyArray<Product>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.order_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.itemView.orderProductTitle.text = currentItem.title
        holder.itemView.orderProductPrice.text = currentItem.price.toString()

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setData(product: Array<Product>){
        this.productList = product
        notifyDataSetChanged()
    }

    fun getSum(): Float {
        var sum = 0.0F
        for(i in productList){
            sum += i.price
        }
        return sum
    }

}