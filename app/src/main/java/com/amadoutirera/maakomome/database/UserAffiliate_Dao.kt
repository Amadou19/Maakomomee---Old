package com.amadoutirera.maakomome.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amadoutirera.maakomome.model.Affiliate
import com.amadoutirera.maakomome.model.User
import io.reactivex.Flowable


@Dao
interface UserAffiliate_Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserAffiliate(affiliate: List<Affiliate>)


    @Query("SELECT * FROM Affiliate WHERE userId = :userId")
    fun getUserAffiliate(userId: String):Flowable<List<Affiliate>>


}