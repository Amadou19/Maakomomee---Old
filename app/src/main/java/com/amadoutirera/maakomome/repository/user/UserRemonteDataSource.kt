package com.amadoutirera.maakomome.repository.user

import androidx.lifecycle.LiveData
import com.amadoutirera.maakomome.database.User_Dao
import com.amadoutirera.maakomome.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleOnSubscribe
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRemonteDataSource @Inject constructor(private val firebaseAuth: FirebaseAuth, private val user_Dao : User_Dao, private val firebaseDatabase: FirebaseFirestore) {
    private lateinit var user: LiveData<User>
    private val userId = firebaseAuth.currentUser!!.uid


    fun getGenres():Single<List<User>> {

        return Single.create(object : SingleOnSubscribe<List<User>>{
            override fun subscribe(emitter: SingleEmitter<List<User>>) {

                firebaseDatabase.collection("User").document(userId)
                        .get()
                        .addOnCompleteListener {
                            if (it.isSuccessful){

                                val userResult = it?.getResult()?.toObject(User::class.java)!!

                                Schedulers.io().scheduleDirect { user_Dao.insertUser(userResult) }
                                Timber.e(userResult.toString())

                            }
                            else{
                                Timber.e("Error getting documents.") }
                        }            }

        })
    }






}