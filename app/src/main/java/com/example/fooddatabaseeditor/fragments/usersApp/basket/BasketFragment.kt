package com.example.fooddatabaseeditor.fragments.usersApp.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddatabaseeditor.R
import com.example.fooddatabaseeditor.data.viewmodel.ProductViewModel
import com.example.fooddatabaseeditor.fragments.usersApp.catalog.CatalogFragmentArgs
import kotlinx.android.synthetic.main.fragment_basket.view.*

class BasketFragment : Fragment() {

    private val args by navArgs<BasketFragmentArgs>()
    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_basket, container, false)

        view.editTextAddress.setText(args.currentUser?.address)

        val adapter = BasketAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.productsRv)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        args.currentProducts?.let { adapter.setData(it) }

        val sumText: String = "Итого " + adapter.getSum().toString() + "Р"
        view.sumTextView.text = sumText

        return view
    }

}