package com.manriquetavi.bakeryapppartner.presentation.screens.product

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manriquetavi.bakeryapppartner.domain.model.Category
import com.manriquetavi.bakeryapppartner.domain.model.Response
import com.manriquetavi.bakeryapppartner.domain.use_cases.firestore.UseCasesFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val useCasesFirestore: UseCasesFirestore
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _allCategories = mutableStateOf<Response<List<Category>?>>(Response.Loading)
    val allCategories: State<Response<List<Category>?>> = _allCategories

    init {
        getAllCategories()
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            useCasesFirestore.getAllCategories().collect {
                _allCategories.value = it
            }
        }
    }
}