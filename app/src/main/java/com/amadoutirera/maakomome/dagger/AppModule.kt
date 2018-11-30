package com.amadoutirera.maakomome.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton



@Module(includes = arrayOf(
        ViewModelModule::class,
        DatabaseModule::class,
        ActivityModule::class,
        FragmentModule::class,
        FirebaseModule::class,
        NetworkModule::class
))

internal class AppModule{

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }
}