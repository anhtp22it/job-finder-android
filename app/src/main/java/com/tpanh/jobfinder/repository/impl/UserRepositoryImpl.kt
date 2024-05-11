package com.tpanh.jobfinder.repository.impl

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.repository.UserRepository
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val fireStore: FirebaseFirestore,
    private val auth: FirebaseAuth
): UserRepository {
    override suspend fun getCurrentUser(): User {
        val userId = auth.currentUser?.uid
        var user = User()
        fireStore.collection("users")
            .document(userId!!)
            .get()
            .addOnSuccessListener {
                user = it.toObject(User::class.java)!!
            }
            .addOnFailureListener {
                Log.e("UserRepositoryImpl", "Error getting documents.", it)
            }.await()
        return user
    }
}