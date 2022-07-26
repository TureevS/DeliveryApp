package com.example.fooddatabaseeditor.fragments.usersApp.login

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
import com.example.fooddatabaseeditor.R
import com.example.fooddatabaseeditor.model.Product
import com.example.fooddatabaseeditor.model.User
import com.example.fooddatabaseeditor.data.viewmodel.ProductViewModel
import com.example.fooddatabaseeditor.data.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_registration.view.*


class RegistrationFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.registerButton.setOnClickListener {
            insertData()
        }

        return view
    }

    private fun insertData() {
        val phone = view?.textPhoneNumber?.text.toString()
        val name = view?.textFirstName?.text.toString()
        val address = view?.textAddress?.text.toString()
        val password = view?.textPassword?.text.toString()

        if(inputCheck(phone, name, address, password)){
            val user = User(phone, name, address, password)
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Вы успешно зарегистрировались", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
        }else{
            Toast.makeText(requireContext(),"Заполните все поля", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(phone: String, name: String, address: String, password: String): Boolean{
        return !(TextUtils.isEmpty(phone) || TextUtils.isEmpty(name) ||
                TextUtils.isEmpty(address) || TextUtils.isEmpty(address) || TextUtils.isEmpty(password))
    }

}