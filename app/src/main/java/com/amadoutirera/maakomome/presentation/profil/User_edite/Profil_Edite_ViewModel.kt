package com.amadoutirera.maakomome.presentation.profil.User_edite

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.amadoutirera.maakomome.model.User
import javax.inject.Inject


class Profil_Edite_ViewModel @Inject constructor() : ViewModel() {
    private val parameter = MutableLiveData<User>()
    private val userId = MutableLiveData<String>()


    sealed class Profiledite_ViewModel_State{
        data class Profiledite_ViewModel_StateError(
                val firstName : String ="",
                val lastName : String ="",
                val telNumber : String ="",
                val profilPic_url : String ="",
                val firstNameErrorVisiblity : Int = View.GONE,
                val lastNameErrorVisiblity : Int = View.GONE,
                val telNumberErrorVisiblity : Int = View.GONE,
                val ProgresseVisiblity : Int = View.GONE) : Profiledite_ViewModel_State()
    }


 /*   private var profiledite_Update_State : LiveData<Profiledite_ViewModel_State> = Transformations.switchMap(parameter ) { parameter ->

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

    fun getUser(newuserId: String) { userId.value = newuserId }


*/

}






