package com.amadoutirera.maakomome.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amadoutirera.maakomome.model.Affiliate
import com.amadoutirera.maakomome.model.User


@Database(entities = [User::class, Affiliate::class],version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun user_Dao():User_Dao
    abstract fun userAffiliate_Dao():UserAffiliate_Dao

}