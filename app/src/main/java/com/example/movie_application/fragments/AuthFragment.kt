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
import com.example.movie_application.USER
import com.example.movie_application.User
import com.example.movie_application.dataBase.DbHelper

class AuthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userLogin: EditText = view.findViewById(R.id.loginAuth)
        val userPassword: EditText = view.findViewById(R.id.passwordAuth)
        val buttonAuth: Button = view.findViewById(R.id.buttonAuth)
        val linkToReg: TextView = view.findViewById(R.id.linkToReg)

        linkToReg.setOnClickListener {
            MAIN.navController.navigate(R.id.action_authFragment_to_regFragment)

        }

        buttonAuth.setOnClickListener {
            val name = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if (name.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Не все поля заполнены", Toast.LENGTH_LONG).show()
            } else {
                val db = DbHelper(requireContext(), null)
                val isAuth = db.getUser(name, password)

                if (isAuth) {
                    Toast.makeText(
                        requireContext(),
                        "Пользователь $name авторизован",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    userLogin.text.clear()
                    userPassword.text.clear()
                    USER = User(name, password)
                    MAIN.navController.navigate(R.id.action_authFragment_to_profileFragment)

                } else
                    Toast.makeText(
                        requireContext(),
                        "Пользователь $name НЕ авторизован",
                        Toast.LENGTH_LONG
                    )
                        .show()


            }
        }
    }
}

