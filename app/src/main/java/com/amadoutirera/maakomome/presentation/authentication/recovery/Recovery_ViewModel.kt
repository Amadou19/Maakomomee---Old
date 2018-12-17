package com.amadoutirera.maakomome.presentation.authentication.recovery

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.domain.repository.authentication.AuthenticationRepository
import com.amadoutirera.maakomome.domain.Failure
import com.amadoutirera.maakomome.utils_extension.Event
import com.google.android.gms.tasks.Task
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.regex.Pattern
import javax.inject.Inject

class Recovery_ViewModel @Inject constructor(private val authenticationRepository: AuthenticationRepository) : ViewModel() {
    val EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private val recoveryState  = MutableLiveData<Recovery_ViewModel_State>()
    private val compositeDisposable = CompositeDisposable()



    sealed class Recovery_ViewModel_State {
        class Editable(@StringRes val tv_Error: Int? = null,
                       var  tv_ErrorVisiblity: Boolean = false,
                       var progressBarVisiblity: Boolean = false,
                       var btnIsEnabled: Boolean = true,
                       @StringRes val snackbarMessage: Event<Int>? = null) : Recovery_ViewModel_State()
        class Success(@StringRes val snackbarMessage: Int? = null)  : Recovery_ViewModel_State()
    }





    /*-------------  --------------*/
    fun getState() : LiveData<Recovery_ViewModel_State> = recoveryState



    /*-------------  --------------*/
    fun passewordRenitialise(emailAdresse: String) {

        recoveryState.value = Recovery_ViewModel_State.Editable(progressBarVisiblity = true, btnIsEnabled = false)

        when {

            !EMAIL_PATTERN.matcher(emailAdresse).matches() -> recoveryState.value =
                    Recovery_ViewModel_State.Editable(tv_Error = R.string.emailFormat, tv_ErrorVisiblity = true)

            else -> authenticationRepository.sendPasswordResetEmail(emailAdresse).either(::handleFailure, ::handleAuthResult)
        }
    }



    /*-------------     --------------*/
    private fun handleFailure(failure: Failure) { recoveryState.value =
            Recovery_ViewModel_State.Editable(snackbarMessage = Event(R.string.offLine)) }


    private fun handleAuthResult(authResult: Single<Task<Void>>) {
        compositeDisposable += authResult
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {

            it.addOnSuccessListener { recoveryState.value = Recovery_ViewModel_State.Success(snackbarMessage = R.string.message_sent) }

            it.addOnFailureListener{
                if (it.message?.contains("is no user record corresponding")!!){
                    recoveryState.value = Recovery_ViewModel_State.Editable(tv_Error = R.string.unregistered_email,tv_ErrorVisiblity = true) }
                else recoveryState.value =             Recovery_ViewModel_State.Editable(snackbarMessage = Event(R.string.serverError))
            }
        }
    }




    /*------------- RX Disposed --------------*/
    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }


}