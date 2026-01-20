package com.literead.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.literead.databinding.FragmentFileExplorerBinding
import com.literead.ui.adapter.DocumentAdapter
import com.literead.viewmodel.FileExplorerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * FileExplorerFragment - Parcourir le stockage
 */
class FileExplorerFragment : Fragment() {
    private var _binding: FragmentFileExplorerBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FileExplorerViewModel by viewModel()
    private lateinit var adapter: DocumentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFileExplorerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        setupClickListeners()
        viewModel.loadDocuments()
    }

    private fun setupRecyclerView() {
        adapter = DocumentAdapter { document ->
            // Ajouter à la bibliothèque et ouvrir
            viewModel.addDocumentToLibrary(document)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupObservers() {
        viewModel.documents.observe(viewLifecycleOwner) { documents ->
            if (documents.isEmpty()) {
                binding.emptyState.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.emptyState.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                adapter.submitList(documents)
            }
        }
    }

    private fun setupClickListeners() {
        binding.sortByNameBtn.setOnClickListener {
            viewModel.setSortOrder(FileExplorerViewModel.SortOrder.BY_NAME)
        }

        binding.sortByDateBtn.setOnClickListener {
            viewModel.setSortOrder(FileExplorerViewModel.SortOrder.BY_DATE)
        }

        binding.sortBySizeBtn.setOnClickListener {
            viewModel.setSortOrder(FileExplorerViewModel.SortOrder.BY_SIZE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
