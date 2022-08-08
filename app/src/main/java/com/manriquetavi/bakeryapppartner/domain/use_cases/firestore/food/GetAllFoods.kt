package com.manriquetavi.bakeryapppartner.domain.use_cases.firestore.food

import com.manriquetavi.bakeryapppartner.data.repository.RepositoryFirestore

class GetAllFoods(
    private val repositoryFirestore: RepositoryFirestore
) {
    suspend operator fun invoke() = repositoryFirestore.getAllFoods()
}