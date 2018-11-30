package com.amadoutirera.maakomome.repository.user

import com.amadoutirera.maakomome.model.User
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import io.reactivex.Flowable

interface UserDatasource {

    val userId : String

    val insertUser: Completable

    //fun getUser(id: String): Flowable<List<User>>
    val getUserProfil: Flowable<List<Comparable<*>>>

}