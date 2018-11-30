package com.amadoutirera.maakomome.worker.authentication

import androidx.work.*
import timber.log.Timber

class Authentication_Worker {

    private val constraintrs = Constraints.Builder()
            .setRequiredNetworkType(NetworkType
                    .CONNECTED).build()


    fun sendEmailNow(){
        Timber.i("Send Email validation and creat UserModel & Affiliate  Now")
        val syncRepositoryWorker = OneTimeWorkRequestBuilder<SendEmailValidation_And_CreatUserModel>()
                .setConstraints(constraintrs)
                .build()

        WorkManager.getInstance()
                .beginUniqueWork("SendEmailValidation_And_creatUserModel", ExistingWorkPolicy.KEEP,syncRepositoryWorker)
                .enqueue()
    }





}