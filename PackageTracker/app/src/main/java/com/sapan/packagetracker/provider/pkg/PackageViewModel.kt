package com.sapan.packagetracker.provider.pkg

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sapan.packagetracker.repository.PackageRepository

class PackageViewModel(private val repository: PackageRepository) : ViewModel() {
    private val _installedPackages = MutableLiveData<List<String>>()
    val installedPackages: LiveData<List<String>>
        get() = _installedPackages

    private val _isHiddenMenuVisible = MutableLiveData<Boolean>()
    val isHiddenMenuVisible: LiveData<Boolean> get() = _isHiddenMenuVisible

    init {
        loadInstalledPackages()
    }

    private fun loadInstalledPackages() {
        val packages = repository.getInstalledPackages()
        _installedPackages.postValue(packages)
        _installedPackages.postValue(packages)
        _isHiddenMenuVisible.postValue(packages.size < 15)
    }
}