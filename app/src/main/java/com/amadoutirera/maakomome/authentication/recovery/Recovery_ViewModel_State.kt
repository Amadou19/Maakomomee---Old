package com.amadoutirera.maakomome.view.authentication

import android.view.View


sealed class Recovery_ViewModel_State (
        val stateText_error : String = "",
        val stateText_errorVisiblity : Int = View.GONE,
        val progressBarVisiblity: Int = View.GONE,
        val snackbarMessage : String = ""

)

class Recovery_ViewModel_State_error(stateText_error: String) :
        Recovery_ViewModel_State(stateText_error = stateText_error, stateText_errorVisiblity = View.VISIBLE, progressBarVisiblity = View.GONE)


class Recovery_ViewModel_State_Success(snackbarMessage :String)  : Recovery_ViewModel_State(snackbarMessage = snackbarMessage)
