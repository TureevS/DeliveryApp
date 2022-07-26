package com.example.fooddatabaseeditor.fragments.databaseManager.adding

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fooddatabaseeditor.R
import com.example.fooddatabaseeditor.model.Product
import com.example.fooddatabaseeditor.data.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        view.addButton.setOnClickListener {
            insertData()
        }

        view.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }

        return view
    }

    private fun insertData() {
        val title = view?.productTitle?.text.toString()
        val category = view?.productCategory?.text.toString()
        val price = requireView().productPrice.text
        val weight = requireView().productWeight.text
        val description = view?.productDescription?.text.toString()

        if(inputCheck(title, category, price, weight, description)){
            val product = Product(0, title, category, price.toString().toFloat(),
                weight.toString().toFloat(), description)
            mProductViewModel.addProduct(product)
            Toast.makeText(requireContext(),"Успешно добавлено", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Заполните все поля", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, category: String, price: Editable, weight: Editable,
                           description: String): Boolean{
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(category) ||
                TextUtils.isEmpty(description) || price.isEmpty() || weight.isEmpty())
    }
}