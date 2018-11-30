package com.amadoutirera.maakomome.home.profil.User_edite.viewmodel

import android.view.View

sealed class Profiledite_ViewModel_State


 data class Profiledite_ViewModel_StateError(
         val firstName : String ="",
         val lastName : String ="",
         val telNumber : String ="",
         val profilPic_url : String ="",
         val firstNameErrorVisiblity : Int = View.GONE,
         val lastNameErrorVisiblity : Int = View.GONE,
         val telNumberErrorVisiblity : Int = View.GONE,
         val ProgresseVisiblity : Int = View.GONE

 ) :Profiledite_ViewModel_State()

