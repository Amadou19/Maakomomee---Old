package com.amadoutirera.maakomome.view.authentication

import androidx.annotation.StringRes


sealed class Sign_ViewModel_State{

class State_editable(
        @StringRes val emailErrorTview: Int ? = null,
        @StringRes val passewordErrorTview: Int ?= null,
        val emailErrorTviewVisiblity: Boolean = false,
        val passewordErrortVisiblity: Boolean = false,
        val progressBarVisiblity: Boolean = false
) : Sign_ViewModel_State()

object State_Success : Sign_ViewModel_State()

}




