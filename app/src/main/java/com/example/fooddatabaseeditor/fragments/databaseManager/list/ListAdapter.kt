package com.example.fooddatabaseeditor.fragments.databaseManager.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddatabaseeditor.R
import com.example.fooddatabaseeditor.model.Product
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var productList = emptyList<Product>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.itemView.text_id.text = currentItem.id.toString()
        holder.itemView.textCategory.text = currentItem.category
        holder.itemView.textTitle.text = currentItem.title
        holder.itemView.textPrice.text = currentItem.price.toString()

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(product: List<Product>){
        this.productList = product
        notifyDataSetChanged()
    }

}