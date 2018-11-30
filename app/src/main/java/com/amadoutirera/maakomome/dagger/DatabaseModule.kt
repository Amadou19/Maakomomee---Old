package com.amadoutirera.maakomome.dagger


import android.app.Application
import androidx.room.Room
import com.amadoutirera.maakomome.database.AppDatabase
import com.amadoutirera.maakomome.database.UserAffiliate_Dao
import com.amadoutirera.maakomome.database.User_Dao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
internal class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app,
            AppDatabase::class.java, "Maakomone.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideUser_Dao(db: AppDatabase): User_Dao {
        return db.user_Dao()
    }


    @Singleton
    @Provides
    fun provideAffiliate_Dao(db: AppDatabase): UserAffiliate_Dao {
        return db.userAffiliate_Dao()
    }



}