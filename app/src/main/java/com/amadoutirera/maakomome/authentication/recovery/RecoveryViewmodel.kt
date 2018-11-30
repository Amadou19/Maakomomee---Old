package com.amadoutirera.maakomome.authentication.recovery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amadoutirera.maakomome.App
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.view.authentication.Recovery_ViewModel_State
import com.amadoutirera.maakomome.view.authentication.Recovery_ViewModel_State_Success
import com.amadoutirera.maakomome.view.authentication.Recovery_ViewModel_State_error
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern
import javax.inject.Inject

class RecoveryViewmodel @Inject constructor(application: Application,private val firebaseAuth: FirebaseAuth) : AndroidViewModel(application) {
    private val revoveryState  = MutableLiveData<Recovery_ViewModel_State>()


    fun getState() : LiveData<Recovery_ViewModel_State> = revoveryState



    fun passewordRenitialise(emailAdresse: String) {
        val EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")

        when {

            !EMAIL_PATTERN.matcher(emailAdresse).matches() -> revoveryState.value = Recovery_ViewModel_State_error(stateText_error = getApplication<Application>().resources.getString(R.string.eamilFormat))

            else -> firebaseAuth.sendPasswordResetEmail(emailAdresse)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful){revoveryState.value = Recovery_ViewModel_State_Success(snackbarMessage = getApplication<Application>().resources.getString(R.string.message_sent)); return@addOnCompleteListener }

                        else  revoveryState.value = Recovery_ViewModel_State_error(stateText_error = getApplication<Application>().resources.getString(R.string.unregistered_email))
                    }
        }
    }
}