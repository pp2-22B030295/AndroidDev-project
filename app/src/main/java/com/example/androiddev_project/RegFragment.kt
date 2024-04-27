package com.example.androiddev_project

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.fragment.app.Fragment
import com.example.androiddev_project.databinding.FragmentRegBinding

class RegFragment : Fragment() {
    private var _binding: FragmentRegBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userLogin: EditText = binding.inputLogin
        val userPassword: EditText = binding.inputPassword
        val button: Button = binding.buttonReg
        val linkToAuth: TextView = binding.linkToAuth

        linkToAuth.setOnClickListener {
            val intent = Intent(requireContext(), AuthFragment::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val name = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if (name.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Не все поля заполнены", Toast.LENGTH_LONG).show()
            } else {
                val user = User(name, password)

                val db = DbHelper(requireContext(), null)

                try {
                    db.addUser(user)
                    Toast.makeText(requireContext(), "Пользователь $name добавлен", Toast.LENGTH_LONG).show()
                } catch (e: SQLiteConstraintException) {
                    Toast.makeText(requireContext(), "Пользователь с таким именем уже существует", Toast.LENGTH_LONG).show()
                }

                userLogin.text.clear()
                userPassword.text.clear()
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
