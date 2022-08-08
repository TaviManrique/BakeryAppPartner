package com.manriquetavi.bakeryapppartner.data.repository

import com.manriquetavi.bakeryapppartner.domain.repository.FirestoreSource
import javax.inject.Inject

class RepositoryFirestore
@Inject constructor(
    private val firestore: FirestoreSource
){
    fun getAllCategories() = firestore.getAllCategories()
    fun getAllFoods() = firestore.getAllFoods()
}