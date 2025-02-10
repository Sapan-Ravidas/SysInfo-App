package com.sapan.packagetracker.repository

import android.content.Intent
import android.content.pm.PackageManager

class PackageRepository(
    private val packageManager: PackageManager
) {
    fun getInstalledPackages() : List<String> {
        val installedPackages = mutableListOf<String>()
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        val resolveInfoList = packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL)

        for (resolveInfo in resolveInfoList) {
            val packageName = resolveInfo.activityInfo.packageName
            installedPackages.add(packageName)
        }
        return installedPackages
    }
}
