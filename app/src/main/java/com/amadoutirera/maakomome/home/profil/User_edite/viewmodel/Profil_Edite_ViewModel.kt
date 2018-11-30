package com.amadoutirera.maakomome.home.profil.User_edite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.amadoutirera.maakomome.model.User
import com.amadoutirera.maakomome.repository.user.UserRepositoryy
import javax.inject.Inject


class Profil_Edite_ViewModel @Inject constructor(userRepository : UserRepositoryy) : ViewModel() {
    private val parameter = MutableLiveData<User>()
    private val userId = MutableLiveData<String>()


    private var profiledite_Update_State : LiveData<Profiledite_ViewModel_State> = Transformations.switchMap(parameter ) { parameter ->

        val firtname = parameter.firstName
        val lastame = parameter.lastName
        val phone = parameter.tel_number
        val image_url = parameter.profilPic

        userRepository.updateUser(firtname,lastame,phone,image_url)
    }


    private var profiledite_State : LiveData<Profiledite_ViewModel_State> = Transformations.switchMap(userId ) { parameter -> userRepository.getUser() }




    fun getprofiledite_Update_State(): LiveData<Profiledite_ViewModel_State> = profiledite_Update_State


    /*------------ function called once --------------*/
    fun getProfile(b: Boolean): LiveData<Profiledite_ViewModel_State> = profiledite_State




    fun updteUser(userProfilEdite: User) { parameter.value = userProfilEdite }

    fun getUser(userId: String) { this.userId.value = userId }




}






