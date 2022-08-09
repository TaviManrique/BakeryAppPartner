package com.manriquetavi.bakeryapppartner.domain.use_cases.firestore.food

import com.manriquetavi.bakeryapppartner.data.repository.RepositoryFirestore

class ChangeOnStockStatus(
    private val repositoryFirestore: RepositoryFirestore
) {
    operator fun invoke(foodId: String, onStock: Boolean) = repositoryFirestore.changeOnStockStatus(foodId, onStock)
}