package com.manriquetavi.bakeryapppartner.di

import com.google.firebase.firestore.FirebaseFirestore
import com.manriquetavi.bakeryapppartner.data.repository.FirestoreSourceImpl
import com.manriquetavi.bakeryapppartner.domain.repository.FirestoreSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestoreRepositoryImpl(firestore: FirebaseFirestore): FirestoreSource = FirestoreSourceImpl(firestore = firestore)
}