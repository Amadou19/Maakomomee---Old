package com.amadoutirera.maakomome.di

import com.amadoutirera.maakomome.presentation.Maakomome_Navigation_Activity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMaakomome_Navigation_Activity(): Maakomome_Navigation_Activity


}