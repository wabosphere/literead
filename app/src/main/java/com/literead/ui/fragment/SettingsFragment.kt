package com.literead.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.literead.R
import com.literead.databinding.ActivitySettingsBinding
import com.literead.theme.ThemeManager
import com.literead.utils.PreferencesManager
import com.literead.viewmodel.ReaderViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * SettingsFragment - Paramètres de l'application
 */
class SettingsFragment : Fragment() {
    private var _binding: ActivitySettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReaderViewModel by viewModel()
    private val preferencesManager: PreferencesManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivitySettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupClickListeners()
    }

    private fun setupUI() {
        val preferences = preferencesManager.getPreferences()

        when (preferences.theme) {
            ThemeManager.THEME_LIGHT -> binding.lightTheme.isChecked = true
            ThemeManager.THEME_DARK -> binding.darkTheme.isChecked = true
            ThemeManager.THEME_SEPIA -> binding.sepiaTheme.isChecked = true
            ThemeManager.THEME_AMOLED -> binding.amoledTheme.isChecked = true
        }

        binding.fontSizeSeekBar.progress = preferences.fontSize

        val isPageMode = preferences.readingMode == "page"
        if (isPageMode) {
            binding.pageMode.isChecked = true
        } else {
            binding.scrollMode.isChecked = true
        }
    }

    private fun setupClickListeners() {
        binding.themeGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.lightTheme.id -> {
                    viewModel.setTheme(ThemeManager.THEME_LIGHT)
                    ThemeManager.applyTheme(requireActivity(), ThemeManager.THEME_LIGHT)
                }
                binding.darkTheme.id -> {
                    viewModel.setTheme(ThemeManager.THEME_DARK)
                    ThemeManager.applyTheme(requireActivity(), ThemeManager.THEME_DARK)
                }
                binding.sepiaTheme.id -> {
                    viewModel.setTheme(ThemeManager.THEME_SEPIA)
                    ThemeManager.applyTheme(requireActivity(), ThemeManager.THEME_SEPIA)
                }
                binding.amoledTheme.id -> {
                    viewModel.setTheme(ThemeManager.THEME_AMOLED)
                    ThemeManager.applyTheme(requireActivity(), ThemeManager.THEME_AMOLED)
                }
            }
        }

        binding.fontSizeSeekBar.setOnSeekBarChangeListener(
            object : android.widget.SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: android.widget.SeekBar, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        binding.fontSizePreview.textSize = progress.toFloat()
                        viewModel.setFontSize(progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: android.widget.SeekBar) {}
                override fun onStopTrackingTouch(seekBar: android.widget.SeekBar) {}
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
