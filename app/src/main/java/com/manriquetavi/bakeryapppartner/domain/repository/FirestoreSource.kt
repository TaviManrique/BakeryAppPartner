package com.manriquetavi.bakeryapppartner.domain.repository

import com.manriquetavi.bakeryapppartner.domain.model.Category
import com.manriquetavi.bakeryapppartner.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface FirestoreSource {
    fun getAllCategories(): Flow<Response<List<Category>?>>
}