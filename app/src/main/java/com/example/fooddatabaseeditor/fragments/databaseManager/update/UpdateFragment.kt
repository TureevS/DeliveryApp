package com.example.fooddatabaseeditor.fragments.databaseManager.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fooddatabaseeditor.R
import com.example.fooddatabaseeditor.model.Product
import com.example.fooddatabaseeditor.data.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        view.updateProductTitle.setText(args.currentProduct.title)
        view.updateProductCategory.setText(args.currentProduct.category)
        view.updateProductPrice.setText(args.currentProduct.price.toString())
        view.updateProductWeight.setText(args.currentProduct.weight.toString())
        view.updateProductDescription.setText(args.currentProduct.description)

        view.updateBackButton.setOnClickListener{
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        view.updateButton.setOnClickListener{
            updateItem()
        }
        
        view.deleteButton.setOnClickListener {
            deleteItem()
        }

        return view
    }

    private fun updateItem() {
        val title = updateProductTitle.text.toString()
        val category = updateProductCategory.text.toString()
        val price = updateProductPrice.text.toString().toFloat()
        val weight = updateProductWeight.text.toString().toFloat()
        val description = updateProductDescription.text.toString()

        if(inputCheck(title, category, updateProductPrice.text, updateProductWeight.text, description)){
            val updatedProduct = Product(args.currentProduct.id, title, category, price, weight, description)
            mProductViewModel.updateProduct(updatedProduct)
            Toast.makeText(requireContext(),"Успешно обновлено", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Заполните все поля", Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да"){_,_ ->
            mProductViewModel.deleteProduct(args.currentProduct)
            Toast.makeText(requireContext(),"Продукт успешно удален", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Нет"){_,_ ->

        }
        builder.setTitle("Удалить выбранный продукт?")
        builder.setMessage("Вы уверены что хотите удалить продукт?")
        builder.create().show()
    }

    private fun inputCheck(title: String, category: String, price: Editable, weight: Editable,
                           description: String): Boolean{
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(category) ||
                TextUtils.isEmpty(description) || price.isEmpty() || weight.isEmpty())
    }
}


