package com.example.fooddatabaseeditor.fragments.usersApp.catalog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddatabaseeditor.R
import com.example.fooddatabaseeditor.fragments.databaseManager.list.ListFragmentDirections
import com.example.fooddatabaseeditor.model.Order
import com.example.fooddatabaseeditor.model.Product
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.product_row.view.*
import kotlinx.coroutines.NonDisposableHandle.parent

class CatalogAdapter(val context: Context): RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    private var productList = emptyList<Product>()

    class CatalogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogAdapter.CatalogViewHolder {
        return CatalogViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false))
    }

    override fun onBindViewHolder(holder: CatalogAdapter.CatalogViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.itemView.productName.text = currentItem.title
        holder.itemView.productWeight.text = currentItem.weight.toString()
        holder.itemView.productCompound.text = currentItem.description
        holder.itemView.addToBasketButton.text = currentItem.price.toString()


        holder.itemView.addToBasketButton.setOnClickListener{
            Order.order += currentItem
            Toast.makeText(context,"Добавлено в корзину", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setData(product: List<Product>){
        this.productList = product
        notifyDataSetChanged()
    }
}