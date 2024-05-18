package com.tpanh.jobfinder.repository.impl

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.repository.JobRepository
import kotlinx.coroutines.tasks.await

class JobRepositoryImpl(
    private val fireStore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : JobRepository {
    override suspend fun getAllJob(): List<Job> {
        val jobs = mutableListOf<Job>()
        val currentUserId = auth.currentUser?.uid
        fireStore.collection("jobs")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val job = document.toObject(Job::class.java)
                    jobs.add(job)
                    Log.d("TAG", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("JobRepositoryImpl", "Error getting documents.", exception)
            }.await()
        return jobs
    }

    override suspend fun getJobByUserId(userId: String): List<Job> {
        val jobs = mutableListOf<Job>()
        fireStore.collection("jobs")
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val job = document.toObject(Job::class.java)
                    jobs.add(job)
                }
            }
            .addOnFailureListener { exception ->
                Log.e("JobRepositoryImpl", "Error getting documents.", exception)
            }
            .await()
        return jobs
    }

    override suspend fun getJobById(id: String): Job {
        Log.d("JobRepositoryImpl", "getJobById: $id")
        var job = Job()
        fireStore.collection("jobs")
            .document(id)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    job = document.toObject(Job::class.java) ?: Job()
                } else {
                    Log.d("JobRepositoryImpl", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("JobRepositoryImpl", "Error getting documents.", exception)
            }.await()
        return job
    }

    override suspend fun getJobByCategory(categoryId: String): List<Job> {
        val jobs = mutableListOf<Job>()
        fireStore.collection("jobs")
            .whereEqualTo("categoryId", categoryId)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val job = document.toObject(Job::class.java)
                    if (job.userId != auth.currentUser?.uid) {
                        jobs.add(job)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("JobRepositoryImpl", "Error getting documents.", exception)
            }.await()
        return jobs
    }

    override suspend fun searchJob(title: String): List<Job> {
        TODO("Not yet implemented")
    }

    override suspend fun postJob(job: Job) {
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
            }.await()
    }
}