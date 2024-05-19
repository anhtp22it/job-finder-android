package com.tpanh.jobfinder.repository.impl

import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.repository.AuthRepository
import com.tpanh.jobfinder.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
): AuthRepository {
    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(value = Resource.Loading())
            val result = auth.signInWithEmailAndPassword(email, password).await()
            emit(value = Resource.Success(data = result))
        }.catch {
            emit(value = Resource.Error(it.message.toString()))
        }
    }

    override fun registerUser(fullName: String, email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(value = Resource.Loading())
            val result = auth.createUserWithEmailAndPassword(email, password).await()

            val user = User(id = result.user?.uid, fullName = fullName, email = email)
            fireStore.collection("users").document(result.user?.uid.toString()).set(user).await()

            emit(value = Resource.Success(data = result))
        }.catch {
            emit(value = Resource.Error(it.message.toString()))
        }
    }

    override fun changePassword(
        oldPassword: String,
        newPassword: String
    ) {
        val user = auth.currentUser
        val credential = EmailAuthProvider.getCredential(user?.email.toString(), oldPassword)
        user?.reauthenticate(credential)
            ?.addOnSuccessListener {
                user.updatePassword(newPassword)
                    .addOnSuccessListener {
                        Log.d("AuthRepositoryImpl", "Password updated.")
                    }
                    .addOnFailureListener {
                        Log.d("AuthRepositoryImpl", "Error password not updated.")
                    }
            }
            ?.addOnFailureListener {
                Log.d("AuthRepositoryImpl", "Error re-authenticate.")
            }
    }

    override fun sendEmailResetPassword(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                Log.d("AuthRepositoryImpl", "Email sent.")
            }
            .addOnFailureListener {
                Log.d("AuthRepositoryImpl", "Error email not sent.")
            }
    }

    override fun logout() {
        auth.signOut()
    }
}