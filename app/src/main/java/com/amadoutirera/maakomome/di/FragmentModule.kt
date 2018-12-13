package com.amadoutirera.maakomome.di


import com.amadoutirera.maakomome.presentation.authentication.login.Login_Fragment
import com.amadoutirera.maakomome.presentation.authentication.recovery.Recovery_Fragment
import com.amadoutirera.maakomome.presentation.authentication.register.Register_Fragment
import com.amadoutirera.maakomome.presentation.declaration.Declaration_Fragment
import com.amadoutirera.maakomome.presentation.historique.Historique_Fragment
import com.amadoutirera.maakomome.presentation.profil.Profil_Fragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeProfil_Fragment(): Profil_Fragment

    @ContributesAndroidInjector
    internal abstract fun contributeSign_Fragment(): Register_Fragment

    @ContributesAndroidInjector
    internal abstract fun contributeLogin_Fragment(): Login_Fragment

    @ContributesAndroidInjector
    internal abstract fun contributeRecovery_Fragment(): Recovery_Fragment

    @ContributesAndroidInjector
    internal abstract fun contributeHistorique_Fragment(): Historique_Fragment

    @ContributesAndroidInjector
    internal abstract fun contributeDeclaration_Fragment(): Declaration_Fragment



}




