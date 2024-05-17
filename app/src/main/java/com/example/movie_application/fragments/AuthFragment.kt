package com.example.movie_application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movie_application.DataBase.DbHelper
import com.example.movie_application.R
import com.example.movie_application.USER
import com.example.movie_application.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {
    lateinit var binding: FragmentAuthBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userLogin: EditText = binding.loginAuth
        val userPassword: EditText = binding.passwordAuth
        val buttonAuth: Button = binding.buttonAuth
        val linkToReg: TextView = binding.linkToReg

        linkToReg.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_regFragment)

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
                    USER = name
                    findNavController().navigate(R.id.action_authFragment_to_profileFragment)

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

