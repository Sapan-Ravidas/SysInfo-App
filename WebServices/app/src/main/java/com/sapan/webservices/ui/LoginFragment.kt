package com.sapan.webservices.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.sapan.webservices.databinding.FragmentLoginBinding
import com.sapan.webservices.models.User
import com.sapan.webservices.network.ApiClient
import com.sapan.webservices.security.SecurityManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {

    private lateinit var _binding: FragmentLoginBinding
    private lateinit var securityManager: SecurityManager

    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        securityManager = SecurityManager(requireContext())
        binding.login.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            // TODO: validate credentials and login
        }
        return binding.root
    }

    /**
     *
     */
    private fun validateCredentials(username: String, password: String) : Boolean {
        return username.isNotEmpty() && password.length >= 3
    }

    private fun loginUser(username: String, password: String) {
        val jsonBody = """
            "username" : "$username",
            "password": "${securityManager.encrypt(password)}
        """.trimIndent()

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.postRequest("login", jsonBody)
                val user = User.fromJson(response)
                // TODO: store encrypted credentials
                withContext(Dispatchers.Main) {
                    // TODO: navigate to main screen
                }
            } catch (e: Exception) {
                // TODO: handle error
            }
        }
    }

}