package com.sapan.packagetracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.sapan.packagetracker.R
import com.sapan.packagetracker.databinding.ActivityMainBinding
import com.sapan.packagetracker.provider.AppbarImageUrlProvider
import com.sapan.packagetracker.ui.about.AboutFragment
import com.sapan.packagetracker.ui.packagelist.PackageFragment

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private var isMenuReady = false
    val binding: ActivityMainBinding
        get() = _binding

    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // enable back navigation

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Handle app bar collapse/expand
        binding.collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.ToolbarTitleStyle)
        binding.collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ToolbarTitleStyle)

        binding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val isCollapsed = Math.abs(verticalOffset) >= (appBarLayout.totalScrollRange - binding.toolbar.height)
            if (isCollapsed) {
                // Collapsed: Align title to start
                binding.collapsingToolbarLayout.title = "Package Tracker"
                binding.collapsingToolbarLayout.setExpandedTitleGravity(Gravity.START)
            } else {
                // Expanded: Center title
                binding.collapsingToolbarLayout.setExpandedTitleGravity(Gravity.CENTER_HORIZONTAL)
            }
        })

        handler.post(imageChangeRunnable)

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, PackageFragment.newInstance())
            .commit()

        val packageFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as? PackageFragment
        packageFragment?.viewModel?.isHiddenMenuVisible?.observe(this, { isVisible ->
            updateHiddenMenuVisibility(isVisible)
        })
    }

    fun updateHiddenMenuVisibility(isVisible: Boolean) {
        if (isMenuReady) {
            val hiddenMenuItem = binding.toolbar.menu.findItem(R.id.menu_hidden)
            hiddenMenuItem?.isVisible = isVisible
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        isMenuReady = true
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_about -> {
                supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainer.id, AboutFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
                true
            }
            R.id.menu_clear_data -> {
                clearAppData()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun clearAppData() {
        Toast.makeText(this, "App data cleared", Toast.LENGTH_SHORT).show()
    }

    private val imageChangeRunnable = object : Runnable {
        override fun run() {
            loadRandomImage()
            handler.postDelayed(this, 5000) // Change image every 5 seconds
        }
    }

    private fun loadRandomImage() {
        val randomImageUrl = AppbarImageUrlProvider.bannerUrls.random()
        Glide.with(this)
            .load(randomImageUrl)
            .into(binding.appbarBanner)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(imageChangeRunnable)
    }
}