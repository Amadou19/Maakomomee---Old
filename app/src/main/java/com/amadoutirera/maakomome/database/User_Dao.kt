package com.amadoutirera.maakomome.database

import androidx.room.*
import com.amadoutirera.maakomome.model.User
import io.reactivex.Completable
import io.reactivex.Flowable


@Dao
interface User_Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)


    @Query("SELECT * FROM User WHERE id = :id")
    fun getUser(id: String): Flowable<List<User>>

    @Update
    fun updateUser(user: User): Completable
}