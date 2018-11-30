package com.amadoutirera.maakomome.authentication.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.view.authentication.Login_ViewModel_State
import com.amadoutirera.maakomome.view.authentication.Login_ViewModel_State_Success
import com.amadoutirera.maakomome.view.authentication.Login_ViewModel_State_error
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


//class Login_ViewModel @Inject constructor(application: Application, private val firebaseAuth: FirebaseAuth) : AndroidViewModel(application) {
class Login_ViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    private val loginState = MutableLiveData<Login_ViewModel_State>()
    private val firebaseAuth = FirebaseAuth.getInstance()


    fun getState():LiveData<Login_ViewModel_State> = loginState
    
    /*-----------------------------------------------------*/

    fun login(email : String , password : String){

        when {
            email.isEmpty() || password.isEmpty() -> {

                loginState.value = Login_ViewModel_State_error(emailOrPassworError = getApplication<Application>().resources.getString(R.string.empty_error))
            }

            else -> { firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful)loginState.value = Login_ViewModel_State_Success

                        else loginState.value = Login_ViewModel_State_error(emailOrPassworError = getApplication<Application>().resources.getString(R.string.email_or_passeword_error));return@addOnCompleteListener
                    }
            }
        }
    }

    /*-----------------------------------------------------*/



}