package com.literead.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.literead.databinding.FragmentLibraryBinding
import com.literead.ui.adapter.DocumentAdapter
import com.literead.viewmodel.LibraryViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * LibraryFragment - Affiche la liste des documents importés
 */
class LibraryFragment : Fragment() {
    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LibraryViewModel by viewModel()
    private lateinit var adapter: DocumentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        adapter = DocumentAdapter { document ->
            // Ouvrir le document
            openDocument(document.id)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupObservers() {
        viewModel.allDocuments.observe(viewLifecycleOwner) { documents ->
            if (documents.isEmpty()) {
                binding.emptyState.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.emptyState.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                adapter.submitList(documents)
            }
        }

        viewModel.searchResults.observe(viewLifecycleOwner) { results ->
            adapter.submitList(results)
        }
    }

    private fun setupClickListeners() {
        binding.searchBar.setOnQueryTextListener(
            object : android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = false

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.search(newText ?: "")
                    return true
                }
            }
        )

        binding.addFilesButton.setOnClickListener {
            // TODO: Ouvrir le file picker
        }
    }

    private fun openDocument(documentId: Long) {
        // TODO: Démarrer ReaderActivity avec le documentId
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
