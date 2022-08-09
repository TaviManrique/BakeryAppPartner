package com.manriquetavi.bakeryapppartner.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.manriquetavi.bakeryapppartner.domain.model.Category
import com.manriquetavi.bakeryapppartner.domain.model.Food
import com.manriquetavi.bakeryapppartner.domain.model.Response
import com.manriquetavi.bakeryapppartner.domain.repository.FirestoreSource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Singleton

@Singleton
class FirestoreSourceImpl(
    private val firestore: FirebaseFirestore
): FirestoreSource {
    var wasOperationSuccessful: Boolean = false
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

    override fun getAllFoods(): Flow<Response<List<Food>?>> = callbackFlow {
        val snapshotListener = firestore
            .collection("foods")
            .addSnapshotListener { snapshot, e ->
                val response =
                    if (snapshot != null) {
                        val foods = snapshot.toObjects(Food::class.java)
                        Response.Success(foods)
                    } else {
                        Response.Error(e?.message ?: e.toString())
                    }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun changeOnStockStatus(foodId: String, onStock: Boolean): Flow<Response<Void?>> = flow {
        try {
            emit(Response.Loading)
            val changeOnStockValue = firestore
                .collection("foods")
                .document(foodId)
                .update("onStock", !onStock)
                .addOnSuccessListener {

                }.await()
            emit(Response.Success(changeOnStockValue))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }


}