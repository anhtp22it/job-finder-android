package com.tpanh.jobfinder.repository.impl

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
    ): Flow<Resource<AuthResult>> {
        return flow {
            emit(value = Resource.Loading())
            val user = auth.currentUser
            user?.updatePassword(newPassword)?.await()
            emit(value = Resource.Success(data = AuthResult::class.java.newInstance()))
        }.catch {
            emit(value = Resource.Error(it.message.toString()))
        }
    }

    override fun sendEmailResetPassword(email: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(value = Resource.Loading())
            auth.sendPasswordResetEmail(email).await()
            emit(value = Resource.Success(data = AuthResult::class.java.newInstance()))
        }.catch {
            emit(value = Resource.Error(it.message.toString()))
        }
    }
}