package com.amadoutirera.maakomome.repository.user

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amadoutirera.maakomome.NetManager
import com.amadoutirera.maakomome.model.User
import io.reactivex.Flowable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import org.reactivestreams.Subscriber
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepository @Inject constructor(private val userLocalDataSource: UserLocalDataSource, private val userRemonteDataSource : UserRemonteDataSource,
                                         private val netManager: NetManager) {
    val compositeDisposable = CompositeDisposable()


    private var profilCombineList = MutableLiveData<List<Comparable<*>>>()



    val user = userRemonteDataSource.getGenres()


    /*------------ Get user profil  Local or Remonte -------------*/
    fun getUser(): Flowable<List<Comparable<*>>> = userLocalDataSource.getUserProfil




}


