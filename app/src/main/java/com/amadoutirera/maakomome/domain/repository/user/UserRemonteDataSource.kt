package com.amadoutirera.maakomome.domain.repository.user

import com.amadoutirera.maakomome.database.User_Dao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRemonteDataSource @Inject
constructor(private val firebaseAuth: FirebaseAuth, private val user_Dao : User_Dao, private val firebaseDatabase: FirebaseFirestore):UserDatasource  {




    override val userId: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.



    override val insertUser: Completable


        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.




    override val getUserProfil: Flowable<List<Comparable<*>>>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.






}