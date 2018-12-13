package com.amadoutirera.maakomome.domain.repository.authentication

import com.amadoutirera.maakomome.domain.Either
import com.amadoutirera.maakomome.domain.Failure
import com.amadoutirera.maakomome.domain.NetworkHandler
import com.amadoutirera.maakomome.domain.RxFirebase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthenticationRemonteDataSource  @Inject constructor(private val firebaseAuth: FirebaseAuth, private val networkHandler: NetworkHandler) : AuthenticationDataSource {




    /*----------        --------------*/
    override fun loginUser(email: String, password: String): Either<Failure, Single<Task<AuthResult>>> {

        return when (networkHandler.isConnected) {
            true ->  Either.ExecutSuccess(RxFirebase.getObservable(firebaseAuth.signInWithEmailAndPassword(email, password))
                    .subscribeOn(Schedulers.io()))
            false ->  Either.ExecutError(Failure.NetworkConnection())
            null ->  Either.ExecutError(Failure.FeatureFailure())
        }
    }



    /*----------        --------------*/
    override fun registertUser(email: String, password: String): Either<Failure, Single<Task<AuthResult>>> {

        return when (networkHandler.isConnected) {
            true ->  Either.ExecutSuccess( RxFirebase.getObservable(firebaseAuth.createUserWithEmailAndPassword(email,password))
                    .subscribeOn(Schedulers.io()))
            false ->  Either.ExecutError(Failure.NetworkConnection())
            null ->  Either.ExecutError(Failure.FeatureFailure())
        }

    }



    /*----------        --------------*/
    override fun sendEmailVerification(): Either<Failure, Single<Task<Void>>> {

        return when (networkHandler.isConnected) {
            true -> Either.ExecutSuccess(RxFirebase.getObservable(firebaseAuth.currentUser!!.sendEmailVerification())
                    .subscribeOn(Schedulers.io()))

            false ->  Either.ExecutError(Failure.NetworkConnection())
            null ->  Either.ExecutError(Failure.FeatureFailure())
        }
    }



    /*----------        --------------*/
    override fun sendPasswordResetEmail(email: String): Either<Failure, Single<Task<Void>>> {

        return when (networkHandler.isConnected) {
            true ->  Either.ExecutSuccess( RxFirebase.getObservable(firebaseAuth.sendPasswordResetEmail(email))
                    .subscribeOn(Schedulers.io()))
            false ->  Either.ExecutError(Failure.NetworkConnection())
            null ->  Either.ExecutError(Failure.FeatureFailure())
        }
    }



    /*----------        --------------*/
    override fun updatePassword(password: String): Either<Failure, Single<Task<Void>>> {

        return when (networkHandler.isConnected) {
            true ->  Either.ExecutSuccess( RxFirebase.getObservable(firebaseAuth.currentUser!!.updatePassword(password))
                    .subscribeOn(Schedulers.io()))
            false ->  Either.ExecutError(Failure.NetworkConnection())
            null ->  Either.ExecutError(Failure.FeatureFailure())
        }
    }








}