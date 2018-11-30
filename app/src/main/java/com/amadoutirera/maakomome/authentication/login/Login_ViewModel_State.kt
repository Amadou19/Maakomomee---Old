package com.amadoutirera.maakomome.view.authentication

import android.view.View


sealed class Login_ViewModel_State (
        val emailOrPassworError : String = "",
        val loginErrorVisiblity : Int = View.GONE,
        val progressBarVisiblity: Int = View.GONE
)

class Login_ViewModel_State_error(emailOrPassworError: String) :
        Login_ViewModel_State(emailOrPassworError = emailOrPassworError, loginErrorVisiblity = View.VISIBLE, progressBarVisiblity = View.GONE)


object Login_ViewModel_State_Success  : Login_ViewModel_State()
