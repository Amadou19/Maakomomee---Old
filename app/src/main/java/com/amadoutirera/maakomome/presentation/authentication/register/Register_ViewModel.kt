package com.amadoutirera.maakomome.presentation.authentication.register

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.domain.repository.authentication.AuthenticationRepository
import com.amadoutirera.maakomome.domain.Failure
import com.amadoutirera.maakomome.utils_extension.Event
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.regex.Pattern
import javax.inject.Inject



class Register_ViewModel @Inject constructor(private val authenticationRepository : AuthenticationRepository): ViewModel() {
    private val EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private val signState = MutableLiveData<Sign_ViewModel_State>()
    private val compositeDisposable = CompositeDisposable()



    sealed class Sign_ViewModel_State {
        class Editable(
                @StringRes var emailErrorTview: Int ? = null,
                @StringRes var passewordErrorTview: Int ?= null,
                var emailErrorTviewVisiblity: Boolean = false,
                var passewordErrortVisiblity: Boolean = false,
                var btnIsEnabled: Boolean = true,
                @StringRes val snackbarMessage: Event<Int>? = null,
                var progressBarVisiblity: Boolean = false) : Sign_ViewModel_State()
        class Success(@StringRes val dialogSuccsessMessage: Event<Int>? = null) : Sign_ViewModel_State()
    }




    /*-------------     --------------*/
    fun getState():LiveData<Sign_ViewModel_State> = signState



    /*-------------     --------------*/
    fun creatUsers(email: String, password: String) {

        signState.value = Sign_ViewModel_State.Editable(progressBarVisiblity = true, btnIsEnabled = false)

        when{
            email.isEmpty() &&  password.isEmpty() || !EMAIL_PATTERN.matcher(email).matches() && password.length !in(7..20) -> signState.value =
                    Sign_ViewModel_State.Editable(emailErrorTview = R.string.email_error, passewordErrorTview = R.string.password_error,
                            emailErrorTviewVisiblity = true, passewordErrortVisiblity = true)

            !EMAIL_PATTERN.matcher(email).matches() -> signState.value =
                    Sign_ViewModel_State.Editable(emailErrorTview = R.string.email_error, emailErrorTviewVisiblity = true)

            password.length !in(7..20)-> signState.value =
                    Sign_ViewModel_State.Editable(passewordErrorTview = R.string.password_error, passewordErrortVisiblity = true)


            else ->  authenticationRepository.registertUser(email, password).either(::handleFailure, ::handleAuthResult)

        }
    }



    /*-------------     --------------*/
    protected fun handleFailure(failure: Failure) { signState.value =
            Sign_ViewModel_State.Editable(snackbarMessage = Event(R.string.offLine)) }

    private fun handleAuthResult(authResult: Single<Task<AuthResult>>) {
        compositeDisposable += authResult
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {

                    it.addOnSuccessListener {
                        signState.value = Sign_ViewModel_State.Success(Event(R.string.sucssec_sign))
                        authenticationRepository.sendEmailVerification() }

                    it.addOnFailureListener{
                        if (it.message?.contains("email address is already in use")!!){ signState.value =
                                Sign_ViewModel_State.Editable(progressBarVisiblity = false, emailErrorTviewVisiblity = true, emailErrorTview = R.string.email_exist)}

                        else{ signState.value = Sign_ViewModel_State.Editable(snackbarMessage = Event(R.string.offLine)) } }
                }
    }







    /*------------- RX Disposed --------------*/
    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }


}