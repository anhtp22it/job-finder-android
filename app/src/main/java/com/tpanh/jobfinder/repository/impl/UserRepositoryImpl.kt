package com.tpanh.jobfinder.repository.impl

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tpanh.jobfinder.model.Education
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.model.WorkExperience
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

    override suspend fun updateUser(user: User) {
        val userId = auth.currentUser?.uid
        fireStore.collection("users")
            .document(userId!!)
            .set(user)
            .addOnSuccessListener {
                Log.d("UserRepositoryImpl", "DocumentSnapshot successfully written!")
            }
            .addOnFailureListener {
                Log.e("UserRepositoryImpl", "Error writing document", it)
            }.await()
    }

    override suspend fun addEducation(userId: String, education: Education) {
        fireStore.collection("users")
            .document(userId)
            .collection("education")
            .add(education)
            .addOnSuccessListener {
                Log.d("UserRepositoryImpl", "DocumentSnapshot successfully written!")
            }
            .addOnFailureListener {
                Log.e("UserRepositoryImpl", "Error writing document", it)
            }.await()
    }

    override suspend fun addWorkExperience(userId: String, workExperience: WorkExperience) {
        fireStore.collection("users")
            .document(userId)
            .collection("workExperience")
            .add(workExperience)
            .addOnSuccessListener {
                Log.d("UserRepositoryImpl", "DocumentSnapshot successfully written!")
            }
            .addOnFailureListener {
                Log.e("UserRepositoryImpl", "Error writing document", it)
            }.await()
    }




}