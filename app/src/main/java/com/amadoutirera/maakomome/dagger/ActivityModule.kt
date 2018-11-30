package com.amadoutirera.maakomome.dagger

import com.amadoutirera.maakomome.Maakomome_Navigation_Activity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMaakomome_Navigation_Activity(): Maakomome_Navigation_Activity


}