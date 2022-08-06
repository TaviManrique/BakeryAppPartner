package com.manriquetavi.bakeryapppartner.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.manriquetavi.bakeryapppartner.domain.model.Category
import com.manriquetavi.bakeryapppartner.domain.model.Response
import com.manriquetavi.bakeryapppartner.domain.repository.FirestoreSource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Singleton

@Singleton
class FirestoreSourceImpl(
    private val firestore: FirebaseFirestore
): FirestoreSource {
    override fun getAllCategories(): Flow<Response<List<Category>?>> = callbackFlow {
        val snapshotListener = firestore
            .collection("categories")
            .orderBy("name")
            .addSnapshotListener { snapshot, e ->
                val response =
                    if (snapshot != null) {
                        val categories = snapshot.toObjects(Category::class.java)
                        Response.Success(categories)
                    } else {
                        Response.Error(e?.message ?: e.toString())
                    }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }
}