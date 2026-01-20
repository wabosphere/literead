package com.literead.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.literead.R
import com.literead.databinding.ActivityMainBinding
import com.literead.ui.fragment.AboutFragment
import com.literead.ui.fragment.FileExplorerFragment
import com.literead.ui.fragment.LibraryFragment
import com.literead.ui.fragment.SettingsFragment
import com.literead.theme.ThemeManager
import com.literead.utils.PreferencesManager
import org.koin.android.ext.android.inject

/**
 * MainActivity - Écran principal avec navigation
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val preferencesManager: PreferencesManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Appliquer le thème avant de charger la vue
        val preferences = preferencesManager.getPreferences()
        ThemeManager.applyTheme(this, preferences.theme)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupBottomNavigation()
    }

    private fun setupViewPager() {
        val adapter = MainPagerAdapter(this)
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false // Désactiver le swipe
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.libraryFragment -> binding.viewPager.currentItem = 0
                R.id.fileExplorerFragment -> binding.viewPager.currentItem = 1
                R.id.settingsFragment -> binding.viewPager.currentItem = 2
                R.id.aboutFragment -> binding.viewPager.currentItem = 3
            }
            true
        }
    }

    /**
     * Adaptateur pour ViewPager2
     */
    inner class MainPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int) = when (position) {
            0 -> LibraryFragment()
            1 -> FileExplorerFragment()
            2 -> SettingsFragment()
            else -> AboutFragment()
        }
    }
}
