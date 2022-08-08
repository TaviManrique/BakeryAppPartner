package com.manriquetavi.bakeryapppartner.di

import com.manriquetavi.bakeryapppartner.data.repository.RepositoryFirestore
import com.manriquetavi.bakeryapppartner.domain.use_cases.firestore.UseCasesFirestore
import com.manriquetavi.bakeryapppartner.domain.use_cases.firestore.categories.GetAllCategories
import com.manriquetavi.bakeryapppartner.domain.use_cases.firestore.food.GetAllFoods
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCasesFirestore(
        repositoryFirestore: RepositoryFirestore
    ): UseCasesFirestore = UseCasesFirestore(
        getAllCategories = GetAllCategories(repositoryFirestore),
        getAllFoods = GetAllFoods(repositoryFirestore)
    )
}