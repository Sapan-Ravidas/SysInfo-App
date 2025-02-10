package com.sapan.packagetracker.ui.packagelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sapan.packagetracker.R
import com.sapan.packagetracker.databinding.FragmentPakcageBinding
import com.sapan.packagetracker.provider.pkg.PackageViewModel
import com.sapan.packagetracker.provider.pkg.PackageViewModelFactory
import com.sapan.packagetracker.repository.PackageRepository
import com.sapan.packagetracker.ui.MainActivity

class PackageFragment private constructor() : Fragment() {

    private lateinit var _binding : FragmentPakcageBinding
    private val binding: FragmentPakcageBinding
        get() = _binding

    lateinit var viewModel: PackageViewModel
    private lateinit var adapter: PackageAdapter

    companion object {
        fun newInstance() : PackageFragment {
            return PackageFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPakcageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val packageManager = requireContext().packageManager
        val repository = PackageRepository(packageManager)
        viewModel = ViewModelProvider(
            this,
            PackageViewModelFactory(repository)
        ).get(PackageViewModel::class.java)

        adapter = PackageAdapter(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.installedPackages.observe(
            viewLifecycleOwner, { packages->
                adapter.submitList(packages)
            }
        )

        viewModel.isHiddenMenuVisible.observe(viewLifecycleOwner, { isVisible ->
            val activity = requireActivity() as MainActivity
            activity.updateHiddenMenuVisibility(isVisible)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}