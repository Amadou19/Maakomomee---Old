package com.amadoutirera.maakomome.dagger


import com.amadoutirera.maakomome.home.declaration.Declaration_Fragment
import com.amadoutirera.maakomome.home.historique.Historique_Fragment
import com.amadoutirera.maakomome.home.profil.Profil_Fragment
import com.amadoutirera.maakomome.view.authentication.Login_Fragment
import com.amadoutirera.maakomome.view.authentication.Recovery_Fragment
import com.amadoutirera.maakomome.view.authentication.Sign_Fragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeProfil_Fragment(): Profil_Fragment

    @ContributesAndroidInjector
    internal abstract fun contributeSign_Fragment(): Sign_Fragment

    @ContributesAndroidInjector
    internal abstract fun contributeLogin_Fragment(): Login_Fragment

    @ContributesAndroidInjector
    internal abstract fun contributeRecovery_Fragment(): Recovery_Fragment

    @ContributesAndroidInjector
    internal abstract fun contributeHistorique_Fragment(): Historique_Fragment

    @ContributesAndroidInjector
    internal abstract fun contributeDeclaration_Fragment(): Declaration_Fragment



}




