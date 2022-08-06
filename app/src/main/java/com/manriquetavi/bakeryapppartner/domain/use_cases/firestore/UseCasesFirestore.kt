package com.manriquetavi.bakeryapppartner.domain.use_cases.firestore

import com.manriquetavi.bakeryapppartner.domain.use_cases.firestore.categories.GetAllCategories

data class UseCasesFirestore(
    val getAllCategories: GetAllCategories
)