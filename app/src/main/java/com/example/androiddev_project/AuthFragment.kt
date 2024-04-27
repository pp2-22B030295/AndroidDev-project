package com.example.androiddev_project

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androiddev_project.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)

        val userLogin: EditText = binding.loginAuth
        val userPassword: EditText = binding.passwordAuth
        val button: Button = binding.buttonAuth
        val linkToReg: TextView = binding.linkToReg


        linkToReg.setOnClickListener {
            val intent = Intent(requireContext(), RegFragment::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val name = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if (name.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Не все поля заполнены", Toast.LENGTH_LONG).show()
            } else {
                val db = DbHelper(requireContext(), null)
                val isAuth = db.getConfirmUser(name, password)

                userLogin.text.clear()
                userPassword.text.clear()

                if (isAuth) {
                    Toast.makeText(requireContext(), "Пользователь $name авторизован", Toast.LENGTH_LONG)
                        .show()
                    userLogin.text.clear()
                    userPassword.text.clear()

                    val intent = Intent(requireContext(), ProfileFragment::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Пользователь $name НЕ авторизован",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
