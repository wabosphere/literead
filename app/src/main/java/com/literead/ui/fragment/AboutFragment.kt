package com.literead.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.literead.R
import com.literead.databinding.FragmentAboutBinding

/**
 * AboutFragment - À propos de l'application
 */
class AboutFragment : Fragment() {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        // Populer les informations
        binding.appVersion.text = getString(R.string.app_version)
        binding.copyright.text = getString(R.string.copyright)
        binding.license.text = getString(R.string.license)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
