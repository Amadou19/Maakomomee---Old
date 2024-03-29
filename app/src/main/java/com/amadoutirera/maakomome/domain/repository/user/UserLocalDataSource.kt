package com.amadoutirera.maakomome.domain.repository.user

import com.amadoutirera.maakomome.database.UserAffiliate_Dao
import com.amadoutirera.maakomome.database.User_Dao
import com.amadoutirera.maakomome.model.Affiliate
import com.amadoutirera.maakomome.model.User
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.rxkotlin.Flowables
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserLocalDataSource
@Inject constructor(private val frbAuth: FirebaseAuth, private val user_Dao : User_Dao, private val userAffiliate_Dao : UserAffiliate_Dao) : UserDatasource {




    /** --------------       ------------ **/
    override val userId: String
        get() = frbAuth.currentUser!!.uid




    /** --------------       ------------ **/
    override val getUserProfil: Flowable<List<Comparable<*>>>
        get() = Flowables.zip(user_Dao.getUser(userId), userAffiliate_Dao.getUserAffiliate(userId), object : Function2<List<User>, List<Affiliate>, List<Comparable<*>>>{

            override fun invoke(user: List<User>, affiliate: List<Affiliate>): List<Comparable<*>> {
                var profilCombineList:MutableList<Comparable<*>> = ArrayList()

                return with(profilCombineList){ user ; affiliate }

            } }).observeOn(Schedulers.io())




    /** -------------------------- **/
    override val insertUser: Completable
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.










}