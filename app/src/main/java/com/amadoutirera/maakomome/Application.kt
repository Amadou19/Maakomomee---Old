package com.amadoutirera.maakomome

import android.app.Application
import com.google.firebase.auth.FirebaseAuth


class Application : Application() {
    lateinit var firebaseAuth : FirebaseAuth
    //var firebaseAuth  = FirebaseAuth.getInstance()


    override fun onCreate() {
        super.onCreate()
        //firebaseAuthh = FirebaseAuth.getInstance()

    }

}