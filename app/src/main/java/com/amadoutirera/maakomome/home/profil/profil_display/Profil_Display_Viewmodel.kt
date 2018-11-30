package com.amadoutirera.maakomome.home.profil.profil_display


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amadoutirera.maakomome.model.User
import com.amadoutirera.maakomome.repository.user.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Profil_Display_Viewmodel  @Inject constructor (application: Application,  private val userRepository : UserRepository) : AndroidViewModel(application) {
    //var profilCombineList = MutableLiveData<Comparable<*>>()
    private val compositeDisposable = CompositeDisposable()
    private var profilCombineList = MutableLiveData<List<Comparable<*>>>()



    fun getUserProfil(): LiveData<List<Comparable<*>>>{

        compositeDisposable +=
                userRepository.getUser()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onNext = {profilCombineList.value = it},
                                onError = { it.printStackTrace() },
                                onComplete = { println("onComplete!")})

        return profilCombineList
    }




    /***********************************************/
    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }

}