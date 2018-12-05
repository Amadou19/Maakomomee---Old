package com.amadoutirera.maakomome.dagger


import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.amadoutirera.maakomome.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule() {

    @Singleton
    @Provides
    fun provideConnectivityManager(app : Application): ConnectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

}