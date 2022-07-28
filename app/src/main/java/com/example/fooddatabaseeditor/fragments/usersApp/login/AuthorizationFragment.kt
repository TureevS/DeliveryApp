package com.example.fooddatabaseeditor.fragments.usersApp.login

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.fooddatabaseeditor.R
import com.example.fooddatabaseeditor.data.viewmodel.UserViewModel
import com.example.fooddatabaseeditor.fragments.databaseManager.list.ListFragmentDirections
import com.example.fooddatabaseeditor.model.User
import kotlinx.android.synthetic.main.fragment_authorization.view.*



class AuthorizationFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_authorization, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.toRegistrationButton.setOnClickListener{
            findNavController().navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }

        view.authButton.setOnClickListener{
            checkUser()
        }

        return view
    }

    private fun checkUser() {
        val phone = view?.editTextTextPhoneNumber?.text.toString()
        val password = view?.editTextTextPassword?.text.toString()
        var user: User?

        if(inputCheck(phone, password)){
            user = mUserViewModel.getUser(phone, password)

            if(user != null){
                Toast.makeText(requireContext(),"Вы успешно авторизовались", Toast.LENGTH_LONG).show()
                if(user.firstName == "admin" && user.password == "admin"){
                    findNavController().navigate(R.id.action_authorizationFragment_to_listFragment)
                }else{
                    val action = AuthorizationFragmentDirections.actionAuthorizationFragmentToCatalogFragment(user)
                    findNavController().navigate(action)
                }

            }else{
                Toast.makeText(requireContext(),"Вы не зарегистрированы", Toast.LENGTH_LONG).show()
            }

        }else{
            Toast.makeText(requireContext(),"Заполните все поля", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(phone: String, password: String): Boolean{
        return !(TextUtils.isEmpty(phone) || TextUtils.isEmpty(password))
    }


}