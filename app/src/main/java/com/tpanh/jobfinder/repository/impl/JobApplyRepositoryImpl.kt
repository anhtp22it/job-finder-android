package com.tpanh.jobfinder.repository.impl

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tpanh.jobfinder.model.JobApply
import com.tpanh.jobfinder.repository.JobApplyRepository

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
}