package com.sapan.packagetracker.ui.packagelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sapan.packagetracker.databinding.ItemPackageBinding

class PackageAdapter (
    private val context: Context // pass context to adapter instead of viewModel
) : RecyclerView.Adapter<PackageAdapter.PackageViewHolder>() {
    private var packages: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageViewHolder {
        val binding = ItemPackageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PackageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PackageViewHolder, position: Int) {
        val packageName = packages[position]
        holder.bind(packageName)
    }

    override fun getItemCount(): Int = packages.size

    fun submitList(newPackages : List<String>) {
        packages = newPackages
        notifyDataSetChanged()
    }

    inner class PackageViewHolder(
        private val binding : ItemPackageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(packageName: String) {
            try {
                val packageInfo = context.packageManager.getPackageInfo(packageName, 0)
                val applicationInfo = context.packageManager.getApplicationInfo(packageName, 0)

                // Set BOTH app name and package name
                binding.appName.text = context.packageManager.getApplicationLabel(applicationInfo)
                binding.packageName.text = packageName // Add this line
                binding.appIcon.setImageDrawable(context.packageManager.getApplicationIcon(applicationInfo))
            } catch (e : Exception) {
                binding.appName.text = "unknown"
                binding.packageName.text = packageName
                binding.appIcon.setImageResource(android.R.drawable.sym_def_app_icon)
            }
        }
    }
}