package com.amadoutirera.maakomome.presentation.authentication.login

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
import timber.log.Timber
import javax.inject.Inject


class Login_ViewModel @Inject constructor(val authenticationRepository: AuthenticationRepository) : ViewModel() {
    private val loginState = MutableLiveData<Login_ViewModel_State>()
    private val compositeDisposable = CompositeDisposable()


    sealed class Login_ViewModel_State {
        class Editable(@StringRes var emailAndPassworError: Int? = null,
                       var loginErrorVisiblity: Boolean = false,
                       @StringRes val  snackbarMessage: Event<Int>? = null,
                       val progressBarVisiblity: Boolean = false) : Login_ViewModel_State()
        object Success : Login_ViewModel_State()
    }


    /*-------------     --------------*/
    fun getState(): LiveData<Login_ViewModel_State> = loginState



    /*-------------     --------------*/
    fun login(email: String, password: String) {

        loginState.value = Login_ViewModel_State.Editable(progressBarVisiblity = true)

        when {
            email.isEmpty() || password.isEmpty() -> loginState.value =
                    Login_ViewModel_State.Editable(emailAndPassworError = R.string.empty_error, loginErrorVisiblity = true)

            else ->  authenticationRepository.loginUser(email, password).either(::handleFailure, ::handleAuthResult)

            }
        }


    /*-------------     --------------*/
    protected fun handleFailure(failure: Failure) { loginState.value =
            Login_ViewModel_State.Editable(snackbarMessage = Event(R.string.offLine)) }


    private fun handleAuthResult(authResult: Single<Task<AuthResult>>) {
        compositeDisposable += authResult
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {

            it.addOnSuccessListener { loginState.value = Login_ViewModel_State.Success }

            it.addOnFailureListener{ loginState.value =
                    Login_ViewModel_State.Editable(emailAndPassworError = R.string.email_or_passeword_error, loginErrorVisiblity = true) }
        }
    }





    /*------------- RX Disposed --------------*/
    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed) compositeDisposable.dispose(); Timber.e("onCleared")
    }

}

