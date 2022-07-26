package com.example.fooddatabaseeditor.data.repository

import androidx.lifecycle.LiveData
import com.example.fooddatabaseeditor.data.dao.ProductDao
import com.example.fooddatabaseeditor.model.Product

class ProductRepository(private val productDao: ProductDao) {

    val readAllData: LiveData<List<Product>> = productDao.readAllData()

    suspend fun addProduct(product: Product){
        productDao.addProduct(product)
    }

    suspend fun updateProduct(product: Product){
        productDao.updateProduct(product)
    }

    suspend fun deleteProduct(product: Product){
        productDao.deleteProduct(product)
    }

}