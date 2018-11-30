package com.amadoutirera.maakomome.authentication.sign

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.view.authentication.Sign_ViewModel_State
import com.amadoutirera.maakomome.view.authentication.Sign_ViewModel_State_Success
import com.amadoutirera.maakomome.view.authentication.Sign_ViewModel_State_error
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern
import javax.inject.Inject


//class Sign_ViewModel @Inject constructor(application: Application, private val firebaseAuth: FirebaseAuth): AndroidViewModel(application) {
class Sign_ViewModel @Inject constructor(private val application: Application, private val firebaseAuth: FirebaseAuth): ViewModel() {



    private val signState = MutableLiveData<Sign_ViewModel_State>()

    /*-----------------------------------------------------*/

    fun getState():LiveData<Sign_ViewModel_State> = signState

    /*-----------------------------------------------------*/

     fun creatUsers(email: String, password: String) {
        val EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")

        when{

            email.isEmpty() &&  password.isEmpty() || !EMAIL_PATTERN.matcher(email).matches() && password.length !in(7..20) -> signState.value = Sign_ViewModel_State_error(
                        emailErrorTview = application.resources.getString(R.string.email_error),
                        passewordEtrrorTview = application.resources.getString(R.string.password_error),
                        emailErrorTviewVisiblity = View.VISIBLE,
                        passewordEtVisiblity = View.VISIBLE,
                        progressBarVisiblity = View.GONE)

            !EMAIL_PATTERN.matcher(email).matches() -> signState.value = Sign_ViewModel_State_error(
                    emailErrorTview = application.resources.getString(R.string.email_error),
                    passewordEtrrorTview = "",
                    emailErrorTviewVisiblity = View.VISIBLE,
                    passewordEtVisiblity = View.GONE,
                    progressBarVisiblity = View.GONE)

            password.length !in(7..20)-> signState.value = Sign_ViewModel_State_error(
                    emailErrorTview = "",
                    passewordEtrrorTview = application.resources.getString(R.string.password_error),
                    emailErrorTviewVisiblity = View.GONE,
                    passewordEtVisiblity = View.VISIBLE,
                    progressBarVisiblity = View.GONE)

            else -> {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (!it.isSuccessful){
                                signState.value = Sign_ViewModel_State_error(
                                        emailErrorTview = application.resources.getString(R.string.email_exist),
                                        passewordEtrrorTview = "",
                                        emailErrorTviewVisiblity = View.VISIBLE,
                                        passewordEtVisiblity = View.GONE,
                                        progressBarVisiblity = View.GONE)

                                return@addOnCompleteListener
                            }
                            else if(it.isSuccessful){
                                signState.value = Sign_ViewModel_State_Success

                            }
                        }
            }
        }

    }



}