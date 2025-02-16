package com.sapan.webservices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sapan.webservices.databinding.ActivityMainBinding
import com.sapan.webservices.ui.RegistrationFragment

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding

    private val binding
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, RegistrationFragment())
            .commit()
    }
}