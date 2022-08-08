package com.manriquetavi.bakeryapppartner.domain.use_cases.firestore

import com.manriquetavi.bakeryapppartner.domain.use_cases.firestore.categories.GetAllCategories
import com.manriquetavi.bakeryapppartner.domain.use_cases.firestore.food.GetAllFoods

data class UseCasesFirestore(
    val getAllCategories: GetAllCategories,
    val getAllFoods: GetAllFoods
)