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
import com.example.movie_application.User
import com.example.movie_application.databinding.FragmentRegBinding

class RegFragment : Fragment() {
    lateinit var binding: FragmentRegBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userLogin: EditText = binding.inputLogin
        val userPassword: EditText = binding.inputPassword
        val buttonReg: Button = binding.buttonReg
        val linkToAuth: TextView = binding.linkToAuth

        linkToAuth.setOnClickListener {
            findNavController().navigate(R.id.action_regFragment_to_authFragment)
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

                findNavController().navigate(R.id.action_regFragment_to_authFragment)
                userLogin.text.clear()
                userPassword.text.clear()
            }
        }

    }
}