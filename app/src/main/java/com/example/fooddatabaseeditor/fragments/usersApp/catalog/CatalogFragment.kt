package com.example.fooddatabaseeditor.fragments.usersApp.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddatabaseeditor.R
import com.example.fooddatabaseeditor.data.viewmodel.ProductViewModel
import com.example.fooddatabaseeditor.fragments.databaseManager.list.ListAdapter
import com.example.fooddatabaseeditor.fragments.databaseManager.update.UpdateFragmentArgs
import com.example.fooddatabaseeditor.fragments.usersApp.login.AuthorizationFragment
import com.example.fooddatabaseeditor.fragments.usersApp.login.AuthorizationFragmentDirections
import com.example.fooddatabaseeditor.model.Order
import com.example.fooddatabaseeditor.model.Product
import kotlinx.android.synthetic.main.fragment_catalog.*
import kotlinx.android.synthetic.main.fragment_catalog.view.*


class CatalogFragment : Fragment() {

    private val args by navArgs<CatalogFragmentArgs>()

    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_catalog, container, false)

        if(args.currentUser != null){
            view.loginButton.setImageResource(R.drawable.ic_baseline_assignment_ind_24)
        }

        val adapter = CatalogAdapter(requireContext())
        val recyclerView = view.findViewById<RecyclerView>(R.id.productsRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        mProductViewModel.readAllData.observe(viewLifecycleOwner, Observer { product ->
            adapter.setData(product)
        })

        view.basketButton.setOnClickListener {
            val action = CatalogFragmentDirections.actionCatalogFragmentToBasketFragment(args.currentUser, Order.order)

            findNavController().navigate(action)
        }

        view.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_catalogFragment_to_authorizationFragment)
        }

        return view
    }

}