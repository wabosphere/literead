package com.literead.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.literead.data.model.Document
import com.literead.databinding.ItemDocumentBinding
import com.literead.utils.FileUtils
import java.text.SimpleDateFormat
import java.util.*

/**
 * Adaptateur pour afficher la liste des documents
 */
class DocumentAdapter(
    private val onItemClick: (Document) -> Unit
) : ListAdapter<Document, DocumentAdapter.DocumentViewHolder>(DocumentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        val binding = ItemDocumentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DocumentViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DocumentViewHolder(
        private val binding: ItemDocumentBinding,
        private val onItemClick: (Document) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(document: Document) {
            binding.documentTitle.text = document.title
            binding.fileSize.text = FileUtils.formatFileSize(document.fileSize)

            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val lastReadDate = dateFormat.format(Date(document.lastReadTime))
            binding.lastRead.text = "Last read: $lastReadDate"

            // Calculer le progrès
            val progress = if (document.totalPages > 0) {
                (document.lastReadPage * 100) / document.totalPages
            } else {
                0
            }
            binding.progressBar.progress = progress

            binding.root.setOnClickListener {
                onItemClick(document)
            }
        }
    }

    class DocumentDiffCallback : DiffUtil.ItemCallback<Document>() {
        override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
            return oldItem == newItem
        }
    }
}
