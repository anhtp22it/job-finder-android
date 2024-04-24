package com.tpanh.jobfinder.data.service.impl

import com.google.firebase.auth.FirebaseAuth
import com.tpanh.jobfinder.data.service.AccountService
import kotlinx.coroutines.tasks.await

class AccountServiceImpl(private val auth: FirebaseAuth) : AccountService {

    override suspend fun createAccountWithEmailAndPassword(
        email: String,
        password: String
    ) {
        auth.createUserWithEmailAndPassword(email, password)
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }
}