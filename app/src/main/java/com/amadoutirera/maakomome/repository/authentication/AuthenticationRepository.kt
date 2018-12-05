package com.amadoutirera.maakomome.repository.authentication

import android.os.AsyncTask
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleOnSubscribe
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthenticationRepository @Inject constructor( private val firebaseAuth: FirebaseAuth){



    /*fun creatUser(email: String, password: String): Task<AuthResult> {

        return firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {

            //Timber.e(it.isSuccessful.toString())
        }
    }*/


    fun creatUser(email: String, password: String): Single<Task<AuthResult>> {
        return Single.create(object : SingleOnSubscribe<Task<AuthResult>> {
            override fun subscribe(emitter: SingleEmitter<Task<AuthResult>>) {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    emitter.onSuccess(it)
                }
            }
        })
    }




}


