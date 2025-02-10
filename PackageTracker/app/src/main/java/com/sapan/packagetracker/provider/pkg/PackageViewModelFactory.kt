package com.sapan.packagetracker.provider.pkg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sapan.packagetracker.repository.PackageRepository

class PackageViewModelFactory(
    private val repository: PackageRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PackageViewModel(repository) as T
    }
}