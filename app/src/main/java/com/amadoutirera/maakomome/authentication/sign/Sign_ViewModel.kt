package com.amadoutirera.maakomome.authentication.sign

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.repository.authentication.AuthenticationRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.regex.Pattern
import javax.inject.Inject




/*class Sign_ViewModel @Inject constructor(private val authenticationRepository : AuthenticationRepository ): ViewModel() {
    val EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private val signState = MutableLiveData<Sign_ViewModel_State>()
    private var signStatevalue = Sign_ViewModel_State.Editable_State()
    private val compositeDisposable = CompositeDisposable()



    sealed class Sign_ViewModel_State{
        class Editable_State(
                @StringRes var emailErrorTview: Int ? = null,
                @StringRes var passewordErrorTview: Int ?= null,
                var emailErrorTviewVisiblity: Boolean = false,
                var passewordErrortVisiblity: Boolean = false,
                var progressBarVisiblity: Boolean = false) : Sign_ViewModel_State()
        object State_Success : Sign_ViewModel_State()
    }




    /*-----------------------------------------------------*/
    fun getState():LiveData<Sign_ViewModel_State> = signState


    /*-----------------------------------------------------*/
    fun creatUsers(email: String, password: String) {

        val emailObservale = Observable.just(email)
                .map { EMAIL_PATTERN.matcher(it).matches() }
                .distinctUntilChanged()
                .map {matcher ->
                    if (matcher) signStatevalue.run { emailErrorTview = null; emailErrorTviewVisiblity = false }
                    else    signStatevalue.run { emailErrorTview = R.string.email_error; emailErrorTviewVisiblity = true }
                    matcher
                }.subscribeOn(Schedulers.io())



        val passwordObservale = Observable.just(password)
                .map { it.length in(7..20)  }
                .distinctUntilChanged()
                .map {matcher ->
                    if (matcher) signStatevalue.run { passewordErrorTview = null; passewordErrortVisiblity = false }
                    else    signStatevalue.run { passewordErrorTview =  R.string.password_error ; passewordErrortVisiblity = true }
                    matcher
                }.subscribeOn(Schedulers.io())



        compositeDisposable += Observable.combineLatest(emailObservale, passwordObservale, object : BiFunction<Boolean, Boolean, Boolean>{
            override fun apply(emailMatcher: Boolean, passwordMatcher: Boolean): Boolean {
                signState.postValue( signStatevalue)
                return emailMatcher && passwordMatcher
            } })
                .subscribeOn(Schedulers.io()) .observeOn(AndroidSchedulers.mainThread())

                .subscribeBy {ok -> if (ok){ subscripbeUser(email, password) } }
    }


    /*-----------------------------------------------------*/
    fun subscripbeUser(email: String, password: String) {

        compositeDisposable += authenticationRepository.creatUser(email,password)
                .subscribeOn(Schedulers.io()) .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {

                   it.addOnSuccessListener {signState.postValue( Sign_ViewModel_State.State_Success)}

                    it.addOnFailureListener{

                        Timber.e(it.localizedMessage)
                    }
                }







    }



    //Timber.e(it.isSuccessful.toString())


    /*------------- RX Disposed --------------*/
    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }


}*/


/*class Sign_ViewModel @Inject constructor(private val authenticationRepository : AuthenticationRepository ): ViewModel() {
    val EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private val signState = MutableLiveData<Sign_ViewModel_State>()
    private var signStatevalue = Sign_ViewModel_State.Editable_State()
    private val compositeDisposable = CompositeDisposable()



    sealed class Sign_ViewModel_State{
        class Editable_State(
                @StringRes var emailErrorTview: Int ? = null,
                @StringRes var passewordErrorTview: Int ?= null,
                var emailErrorTviewVisiblity: Boolean = false,
                var passewordErrortVisiblity: Boolean = false,
                var progressBarVisiblity: Boolean = false) : Sign_ViewModel_State()
        object State_Success : Sign_ViewModel_State()
    }




    /*-----------------------------------------------------*/
    fun getState():LiveData<Sign_ViewModel_State> = signState


    /*-----------------------------------------------------*/
    fun creatUsers(email: String, password: String) {

        signStatevalue.run { progressBarVisiblity = true }

        val emailObservale = Observable.just(email)
                .map { EMAIL_PATTERN.matcher(it).matches() }
                .map {matcher ->
                    if (matcher) signStatevalue.run { emailErrorTview = null; emailErrorTviewVisiblity = false}
                    else    signStatevalue.run { emailErrorTview = R.string.email_error; emailErrorTviewVisiblity = true; progressBarVisiblity = false}
                    matcher
                }.subscribeOn(Schedulers.io())



        val passwordObservale = Observable.just(password)
                .map { it.length in(7..20)  }
                .map {matcher ->
                    if (matcher) signStatevalue.run { passewordErrorTview = null; passewordErrortVisiblity = false }
                    else    signStatevalue.run { passewordErrorTview =  R.string.password_error ; passewordErrortVisiblity = true; progressBarVisiblity = false }
                    matcher
                }.subscribeOn(Schedulers.io())



        compositeDisposable += Observable.combineLatest(emailObservale, passwordObservale, object : BiFunction<Boolean, Boolean, Boolean>{
            override fun apply(emailMatcher: Boolean, passwordMatcher: Boolean): Boolean {
                signState.postValue( signStatevalue)
                return emailMatcher && passwordMatcher
            } })
                .subscribeOn(Schedulers.io()) .observeOn(AndroidSchedulers.mainThread())

                .subscribeBy {ok -> if (ok){ subscripbeUser(email, password) } }
    }


    /*-----------------------------------------------------*/
    fun subscripbeUser(email: String, password: String) {

        compositeDisposable += authenticationRepository.creatUser(email,password)
                .subscribeOn(Schedulers.io()) .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {

                   it.addOnSuccessListener { signState.postValue( Sign_ViewModel_State.State_Success) }

                    it.addOnFailureListener{

                        signStatevalue.run { progressBarVisiblity = false }
                        signState.postValue( signStatevalue)



                        Timber.e(it.localizedMessage)

                    }
                }







    }



    //Timber.e(it.isSuccessful.toString())


    /*------------- RX Disposed --------------*/
    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }


}*/


class Sign_ViewModel @Inject constructor(private val authenticationRepository : AuthenticationRepository ): ViewModel() {
    val EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private val signState = MutableLiveData<Sign_ViewModel_State>()
    private var signStatevalue = Sign_ViewModel_State.Editable_State()
    private val compositeDisposable = CompositeDisposable()



    sealed class Sign_ViewModel_State{
        class Editable_State(
                @StringRes var emailErrorTview: Int ? = null,
                @StringRes var passewordErrorTview: Int ?= null,
                var emailErrorTviewVisiblity: Boolean = false,
                var passewordErrortVisiblity: Boolean = false,
                var progressBarVisiblity: Boolean = false) : Sign_ViewModel_State()
        object State_Success : Sign_ViewModel_State()
    }




    /*-----------------------------------------------------*/
    fun getState():LiveData<Sign_ViewModel_State> = signState


    /*-----------------------------------------------------*/
    fun creatUsers(email: String, password: String) {

        val emailObservale = Observable.just(email)
                .map { EMAIL_PATTERN.matcher(it).matches() }
                .distinctUntilChanged()
                .map {matcher ->
                    if (matcher) signStatevalue.run { emailErrorTview = null; emailErrorTviewVisiblity = false }
                    else    signStatevalue.run { emailErrorTview = R.string.email_error; emailErrorTviewVisiblity = true }
                    matcher
                }.subscribeOn(Schedulers.io())



        val passwordObservale = Observable.just(password)
                .map { it.length in(7..20)  }
                .distinctUntilChanged()
                .map {matcher ->
                    if (matcher) signStatevalue.run { passewordErrorTview = null; passewordErrortVisiblity = false }
                    else    signStatevalue.run { passewordErrorTview =  R.string.password_error ; passewordErrortVisiblity = true }
                    matcher
                }.subscribeOn(Schedulers.io())



        compositeDisposable += Observable.combineLatest(emailObservale, passwordObservale, object : BiFunction<Boolean, Boolean, Boolean>{
            override fun apply(emailMatcher: Boolean, passwordMatcher: Boolean): Boolean {
                signState.postValue( signStatevalue)
                return emailMatcher && passwordMatcher
            } })
                .subscribeOn(Schedulers.io()) .observeOn(AndroidSchedulers.mainThread())

                .subscribeBy {ok -> if (ok){ subscripbeUser(email, password) } }
    }


    /*-----------------------------------------------------*/
    fun subscripbeUser(email: String, password: String) {

        compositeDisposable += authenticationRepository.creatUser(email,password)
                .subscribeOn(Schedulers.io()) .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {

                   it.addOnSuccessListener {signState.postValue( Sign_ViewModel_State.State_Success)}

                    it.addOnFailureListener{

                        Timber.e(it.localizedMessage)
                    }
                }







    }



    //Timber.e(it.isSuccessful.toString())


    /*------------- RX Disposed --------------*/
    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }


}