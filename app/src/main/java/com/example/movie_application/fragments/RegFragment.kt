package com.example.movie_application.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.movie_application.MAIN
import com.example.movie_application.R
import com.example.movie_application.User
import com.example.movie_application.dataBase.DbHelper

class RegFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reg, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userLogin: EditText = view.findViewById(R.id.inputLogin)
        val userPassword: EditText = view.findViewById(R.id.inputPassword)
        val buttonReg: Button = view.findViewById(R.id.buttonReg)
        val linkToAuth: TextView = view.findViewById(R.id.linkToAuth)

        linkToAuth.setOnClickListener {
            MAIN.navController.navigate(R.id.action_regFragment_to_authFragment)
        }

        buttonReg.setOnClickListener {
            val name = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if (name.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Не все поля заполнены", Toast.LENGTH_LONG).show()
            } else {
                val user = User(name, password)

                val db = DbHelper(requireContext(), null)
                db.addUser(user)

                Toast.makeText(
                    requireContext(),
                    "Пользователь $name добавлен",
                    Toast.LENGTH_LONG
                ).show()

                MAIN.navController.navigate(R.id.action_regFragment_to_authFragment)
                userLogin.text.clear()
                userPassword.text.clear()
            }
        }





    }


}