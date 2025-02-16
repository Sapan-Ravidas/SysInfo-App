package com.sapan.webservices.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.sapan.webservices.R
import com.sapan.webservices.databinding.FragmentLoginBinding
import com.sapan.webservices.databinding.FragmentRegistrationBinding
import com.sapan.webservices.models.User
import com.sapan.webservices.network.ApiClient
import com.sapan.webservices.security.SecurityManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationFragment : Fragment() {

    private lateinit var _binding: FragmentRegistrationBinding

    private val binding
        get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        binding.register.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            if (validateCredentials(username, password)) {
                registerUser(username, password)
            }
        }
        return binding.root
    }

    private fun validateCredentials(username: String, password: String) : Boolean {
        return username.isNotEmpty() && password.length >= 3
    }

    private fun registerUser(username : String, password : String) {
        val json = User(username = username, password = password).toJson()
        lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                binding.progresBar.visibility = View.VISIBLE
            }
            try {
                val response = ApiClient.postRequest("public/create-user", json)
                val user = User.fromJson(response)
                withContext(Dispatchers.Main) {
                    binding.progresBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Registration successful!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    binding.progresBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Registration fail!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}