package com.tpanh.jobfinder.repository.impl

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tpanh.jobfinder.model.ApplicationStatus
import com.tpanh.jobfinder.model.JobApply
import com.tpanh.jobfinder.repository.JobApplyRepository
import kotlinx.coroutines.tasks.await

class JobApplyRepositoryImpl(
    private val fireStore: FirebaseFirestore,
    private val auth: FirebaseAuth
): JobApplyRepository {
    override suspend fun applyJob(apply: JobApply) {
        val user = auth.currentUser
        apply.userId = user?.uid.toString()
        if (user != null) {
            val newJobApplyId = fireStore.collection("jobApplies").document().id
            apply.id = newJobApplyId
            fireStore.collection("jobApplies")
                .document(newJobApplyId)
                .set(apply)
                .addOnSuccessListener {
                    Log.d("JobApplyRepository", "Job apply success")
                }
                .addOnFailureListener {
                    Log.e("JobApplyRepository", "Job apply failed", it)
                }
        }
    }

    override suspend fun getMyApplies(): List<JobApply> {
        val user = auth.currentUser
        val applies = mutableListOf<JobApply>()
        if (user != null) {
            fireStore.collection("jobApplies")
                .whereEqualTo("userId", user.uid)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val apply = document.toObject(JobApply::class.java)
                        applies.add(apply)
                    }
                }
                .addOnFailureListener {
                    Log.e("JobApplyRepository", "Get my applies failed", it)
                }.await()
        }
        return applies
    }

    override suspend fun getApplyById(id: String): JobApply {
        var apply = JobApply()
        fireStore.collection("jobApplies")
            .document(id)
            .get()
            .addOnSuccessListener {
                apply = it.toObject(JobApply::class.java)!!
            }
            .addOnFailureListener {
                Log.e("JobApplyRepository", "Get apply by id failed", it)
            }.await()
        return apply
    }

    override suspend fun getApplyByJobId(jobId: String): List<JobApply> {
        val applies = mutableListOf<JobApply>()
        fireStore.collection("jobApplies")
            .whereEqualTo("job.id", jobId)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val apply = document.toObject(JobApply::class.java)
                    applies.add(apply)
                }
            }
            .addOnFailureListener {
                Log.e("JobApplyRepository", "Get apply by job id failed", it)
            }.await()
        return applies
    }

    override suspend fun acceptApplication(applyId: String) {
        val applyRef = fireStore.collection("jobApplies").document(applyId)
        fireStore.runTransaction { transaction ->
            val apply = transaction.get(applyRef).toObject(JobApply::class.java)
            apply?.status = ApplicationStatus.ACCEPT
            transaction.set(applyRef, apply!!)
        }.await()
    }

    override suspend fun rejectApplication(applyId: String) {
        val applyRef = fireStore.collection("jobApplies").document(applyId)
        fireStore.runTransaction { transaction ->
            val apply = transaction.get(applyRef).toObject(JobApply::class.java)
            apply?.status = ApplicationStatus.REJECT
            transaction.set(applyRef, apply!!)
        }.await()
    }
}