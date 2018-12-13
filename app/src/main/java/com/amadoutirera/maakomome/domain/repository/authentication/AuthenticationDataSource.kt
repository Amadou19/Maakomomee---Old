package com.amadoutirera.maakomome.domain.repository.authentication

import com.amadoutirera.maakomome.domain.Either
import com.amadoutirera.maakomome.domain.Failure
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import io.reactivex.Single



interface AuthenticationDataSource {



    fun loginUser(email: String, password: String) : Either<Failure, Single<Task<AuthResult>>>



    fun registertUser(email: String, password: String) : Either<Failure, Single<Task<AuthResult>>>



    fun sendEmailVerification() : Either<Failure, Single<Task<Void>>>



    fun sendPasswordResetEmail(email: String) : Either<Failure, Single<Task<Void>>>



    fun updatePassword(password: String) : Either<Failure, Single<Task<Void>>>



}