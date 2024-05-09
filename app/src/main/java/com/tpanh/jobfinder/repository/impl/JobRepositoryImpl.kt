package com.tpanh.jobfinder.repository.impl

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.repository.JobRepository

class JobRepositoryImpl(
    private val fireStore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage
): JobRepository {
    override fun getAllJob(): List<Job> {
        TODO("Not yet implemented")
    }

    override fun getJobById(id: String): Job {
        TODO("Not yet implemented")
    }

    override fun getJobByCategory(category: String): List<Job> {
        TODO("Not yet implemented")
    }

    override fun searchJob(title: String): List<Job> {
        TODO("Not yet implemented")
    }

    override fun postJob(job: Job) {
        val newJobRef = fireStore.collection("jobs").document()
        job.id = newJobRef.id
        job.userId = auth.currentUser?.uid ?: ""
        newJobRef
            .set(job)
            .addOnSuccessListener {
                Log.d("JobRepositoryImpl", "DocumentSnapshot added with ID: ${newJobRef.id}")
            }
            .addOnFailureListener {
                Log.e("JobRepositoryImpl", "Error adding document", it)
            }
    }
}