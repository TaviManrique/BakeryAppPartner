package com.manriquetavi.bakeryapppartner.domain.use_cases.firestore.categories

import com.manriquetavi.bakeryapppartner.data.repository.RepositoryFirestore

class GetAllCategories(
    private val repositoryFirestore: RepositoryFirestore
) {
    operator fun invoke() = repositoryFirestore.getAllCategories()
}