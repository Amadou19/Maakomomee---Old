package com.amadoutirera.maakomome

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.amadoutirera.maakomome.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject



class App : MultiDexApplication() , HasActivityInjector, HasSupportFragmentInjector {
    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>



    override fun onCreate() {
        super.onCreate()


        /*------------------------------*/
        MultiDex.install(this);


        /*------------ DI ---------------*/
        DaggerAppComponent.builder().application(this).build().inject(this)


        /*------------------------------*/
        Stetho.initializeWithDefaults(this);


        /*------------------------------*/
        Timber.plant(Timber.DebugTree())


    }




    /*------------------------------*/
    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    /*------------------------------*/
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector


}