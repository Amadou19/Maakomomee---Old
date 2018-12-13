package com.amadoutirera.maakomome.domain.repository.user

import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepository @Inject constructor(private val userLocalDataSource: UserLocalDataSource, private val userRemonteDataSource : UserRemonteDataSource) : UserDatasource  {



    override val userId: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.




    override val insertUser: Completable
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.






    /*------------ Get user profil  Local or Remonte -------------*/
    override val getUserProfil: Flowable<List<Comparable<*>>>
        get() = userLocalDataSource.getUserProfil





}


