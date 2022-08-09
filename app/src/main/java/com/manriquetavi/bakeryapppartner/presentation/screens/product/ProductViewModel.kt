package com.manriquetavi.bakeryapppartner.presentation.screens.product

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manriquetavi.bakeryapppartner.domain.model.Category
import com.manriquetavi.bakeryapppartner.domain.model.Food
import com.manriquetavi.bakeryapppartner.domain.model.Response
import com.manriquetavi.bakeryapppartner.domain.use_cases.firestore.UseCasesFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
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

    private val _searchedFoods = mutableStateOf<Response<List<Food>?>>(Response.Loading)
    val searchedFoods: MutableState<Response<List<Food>?>> = _searchedFoods

    private val _changeOnStockState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val changeOnStockState: State<Response<Void?>> = _changeOnStockState

    init {
        getAllCategories()
        getAllFoodsSearched()
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun changeOnStockStatus(foodId: String, onStock: Boolean) {
        viewModelScope.launch {
            useCasesFirestore.changeOnStockStatus(foodId, onStock).collect { response ->
                _changeOnStockState.value = response
            }
        }
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            useCasesFirestore.getAllCategories().collect {
                _allCategories.value = it
            }
        }
    }
    private fun getAllFoodsSearched() {
        viewModelScope.launch {
            useCasesFirestore.getAllFoods().collect {
                _searchedFoods.value = it
            }
        }
    }
}