package com.literead.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.literead.data.model.Document
import com.literead.data.repository.DocumentRepository
import kotlinx.coroutines.launch

/**
 * ViewModel pour l'explorateur de fichiers
 */
class FileExplorerViewModel(private val repository: DocumentRepository) : ViewModel() {
    private val _documents = MutableLiveData<List<Document>>()
    val documents: LiveData<List<Document>> = _documents

    private val _sortOrder = MutableLiveData<SortOrder>()
    val sortOrder: LiveData<SortOrder> = _sortOrder

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    enum class SortOrder {
        BY_NAME, BY_DATE, BY_SIZE
    }

    init {
        _sortOrder.value = SortOrder.BY_DATE
    }

    fun loadDocuments() {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                // Implementation: scan files from storage
                _loadingState.value = false
            } catch (e: Exception) {
                _loadingState.value = false
            }
        }
    }

    fun setSortOrder(order: SortOrder) {
        _sortOrder.value = order
    }

    fun addDocumentToLibrary(document: Document) {
        viewModelScope.launch {
            repository.addDocument(document)
        }
    }
}
