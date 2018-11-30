package com.amadoutirera.maakomome.view.authentication

import android.view.View


sealed class Sign_ViewModel_State


class Sign_ViewModel_State_error(
        val emailErrorTview: String ? = "",
        val passewordEtrrorTview: String ?= "",
        val emailErrorTviewVisiblity: Int = View.GONE,
        val passewordEtVisiblity: Int = View.GONE,
        val progressBarVisiblity: Int = View.GONE
)
    : Sign_ViewModel_State()


object Sign_ViewModel_State_Success : Sign_ViewModel_State()




/*sealed class Sign_ViewModel_State (
        val emailErrorTview: String ? = "",
        val passewordEtrrorTview: String ?= "",
        val emailErrorTviewVisiblity: Int = View.GONE,
        val passewordEtVisiblity: Int = View.GONE,
        val progressBarVisiblity: Int = View.GONE
)


class Sign_ViewModel_State_error(
        emailErrorTview: String,
        passewordEtrrorTview: String,
        emailErrorTviewVisiblity: Int,
        passewordEtVisiblity: Int,
        progressBarVisiblity: Int
)
    : Sign_ViewModel_State(

        emailErrorTview = emailErrorTview,
        passewordEtrrorTview = passewordEtrrorTview,
        emailErrorTviewVisiblity  = emailErrorTviewVisiblity,
        passewordEtVisiblity = passewordEtVisiblity,
        progressBarVisiblity = progressBarVisiblity
)


object Sign_ViewModel_State_Success : Sign_ViewModel_State()

*/



