package com.amadoutirera.maakomome.domain.repository.user

import com.amadoutirera.maakomome.model.User
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import io.reactivex.Flowable

interface UserDatasource {

    val userId : String

    val insertUser: Completable

    val getUserProfil: Flowable<List<Comparable<*>>>

}