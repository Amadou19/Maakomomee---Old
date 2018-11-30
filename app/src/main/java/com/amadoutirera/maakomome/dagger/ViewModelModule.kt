package com.amadoutirera.maakomome.dagger


import androidx.lifecycle.ViewModel
import com.amadoutirera.maakomome.authentication.login.Login_ViewModel
import com.amadoutirera.maakomome.authentication.recovery.RecoveryViewmodel
import com.amadoutirera.maakomome.authentication.sign.Sign_ViewModel
import com.amadoutirera.maakomome.home.profil.User_edite.viewmodel.Profil_Edite_ViewModel
import com.amadoutirera.maakomome.home.profil.profil_display.Profil_Display_Viewmodel
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
    @ViewModelKey(Sign_ViewModel::class)
    abstract fun bindSign_ViewModel(viewModel : Sign_ViewModel) : ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(Login_ViewModel::class)
    abstract fun bindLogin_ViewModel(viewModel : Login_ViewModel) : ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(RecoveryViewmodel::class)
    abstract fun bindRecoveryViewmodel(viewModel : RecoveryViewmodel) : ViewModel




}