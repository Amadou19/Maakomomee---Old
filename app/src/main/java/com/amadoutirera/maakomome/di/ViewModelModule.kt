package com.amadoutirera.maakomome.di


import androidx.lifecycle.ViewModel
import com.amadoutirera.maakomome.presentation.authentication.login.Login_ViewModel
import com.amadoutirera.maakomome.presentation.authentication.recovery.Recovery_ViewModel
import com.amadoutirera.maakomome.presentation.authentication.register.Register_ViewModel
import com.amadoutirera.maakomome.presentation.profil.User_edite.Profil_Edite_ViewModel
import com.amadoutirera.maakomome.presentation.profil.profil_display.Profil_Display_Viewmodel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(Profil_Display_Viewmodel::class)
    abstract fun bindProfil_Display_Viewmodel(viewModel : Profil_Display_Viewmodel) : ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(Profil_Edite_ViewModel::class)
    abstract fun bindProfil_Edite_ViewModel(viewModel : Profil_Edite_ViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(Register_ViewModel::class)
    abstract fun bindSign_ViewModel(viewModel : Register_ViewModel) : ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(Login_ViewModel::class)
    abstract fun bindLogin_ViewModel(viewModel : Login_ViewModel) : ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(Recovery_ViewModel::class)
    abstract fun bindRecoveryViewmodel(viewModel : Recovery_ViewModel) : ViewModel




}