package com.amadoutirera.maakomome.domain.repository.authentication

import com.amadoutirera.maakomome.domain.Either
import com.amadoutirera.maakomome.domain.Failure
import com.google.android.gms.tasks.Task
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthenticationRepository @Inject constructor(private val  authenticationRemonteDataSource: AuthenticationRemonteDataSource) : AuthenticationDataSource {


    /*----------        --------------*/
    override fun loginUser(email: String, password: String)
            = authenticationRemonteDataSource.loginUser(email, password)



    /*----------        --------------*/
    override fun registertUser(email: String, password: String)
            = authenticationRemonteDataSource.registertUser(email, password)



    /*----------        --------------*/
    override fun sendPasswordResetEmail(email: String)
            = authenticationRemonteDataSource.sendPasswordResetEmail(email)


    /*----------        --------------*/
    override fun sendEmailVerification(): Either<Failure, Single<Task<Void>>>
            = authenticationRemonteDataSource.sendEmailVerification()



    /*----------        --------------*/
    override fun updatePassword(password: String)
            = authenticationRemonteDataSource.updatePassword(password)



}



