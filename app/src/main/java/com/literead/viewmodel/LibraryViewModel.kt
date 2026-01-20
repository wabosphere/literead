package com.literead.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.literead.data.model.Document
import com.literead.data.repository.DocumentRepository
import kotlinx.coroutines.launch

/**
 * ViewModel pour la Bibliothèque (Library)
 */
class LibraryViewModel(private val repository: DocumentRepository) : ViewModel() {
    private val _searchQuery = MutableLiveData<String>("")
    val searchQuery: LiveData<String> = _searchQuery

    private val _searchResults = MutableLiveData<List<Document>>()
    val searchResults: LiveData<List<Document>> = _searchResults

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    val allDocuments: LiveData<List<Document>> = repository.getAllDocuments()
    val recentDocuments: LiveData<List<Document>> = repository.getRecentDocuments()

    fun search(query: String) {
        _searchQuery.value = query
        if (query.isBlank()) {
            _searchResults.value = emptyList()
            return
        }

        _loadingState.value = true
        viewModelScope.launch {
            try {
                val results = repository.searchDocuments(query)
                _searchResults.value = results
            } finally {
                _loadingState.value = false
            }
        }
    }

    fun deleteDocument(documentId: Long) {
        viewModelScope.launch {
            repository.deleteDocument(documentId)
        }
    }

    fun clearSearch() {
        _searchQuery.value = ""
        _searchResults.value = emptyList()
    }
}
