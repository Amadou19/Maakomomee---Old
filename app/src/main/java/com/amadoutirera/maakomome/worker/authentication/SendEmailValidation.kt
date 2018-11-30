package com.amadoutirera.maakomome.worker.authentication

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.amadoutirera.maakomome.model.Affiliate
import com.amadoutirera.maakomome.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import timber.log.Timber
import javax.inject.Inject

class SendEmailValidation_And_CreatUserModel @Inject constructor(context : Context, params : WorkerParameters,
                                                                 private val firestoreDb : FirebaseFirestore, private val auth: FirebaseAuth) : Worker(context, params) {

    private val user = auth.currentUser

    val settings = FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build()



    override fun doWork(): Result {
        firestoreDb.firestoreSettings = settings

        Timber.i("Sendind Email called...")


        /* ------- Send Email for validation *------------*/
        user?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) Timber.d( "Email sent.")
            else Timber.d( "Email sent failled.")
        }


        /*-----* Creat User Model and User Affiliate *----*/
        firestoreDb.collection("User").document(user!!.uid).set(User()).addOnCompleteListener {
            if (it.isSuccessful)
                else{}
        }

        firestoreDb.collection("User").document(user.uid).collection("Affiliate").document().set(Affiliate()).addOnCompleteListener {
            if (it.isSuccessful)
            else{}
        }



        return Result.SUCCESS

    }
}

//FieldValue.serverTimestamp())
