package com.amadoutirera.maakomome.repository.user

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amadoutirera.maakomome.AppExecutors
import com.amadoutirera.maakomome.database.User_Dao
import com.amadoutirera.maakomome.home.profil.User_edite.viewmodel.Profiledite_ViewModel_State
import com.amadoutirera.maakomome.home.profil.User_edite.viewmodel.Profiledite_ViewModel_StateError
import com.amadoutirera.maakomome.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.QueryDocumentSnapshot
import timber.log.Timber
import java.util.regex.Pattern
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepositoryy @Inject constructor(private val firestoreDb : FirebaseFirestore, private val user_Dao : User_Dao, private val appExecutors : AppExecutors)  {
    //private val firestoreDb =  FirebaseFirestore.getInstance()
    private val userId = FirebaseAuth.getInstance().currentUser!!.uid
    private var profilState = MutableLiveData<Profiledite_ViewModel_State>()
    private val user = MutableLiveData<User>()
    var profilCombineList = MutableLiveData<Comparable<*>>()





    /*------------ Get user profil for Display in Database -------------*/
    fun getUserProfile_db():LiveData<User> {

        appExecutors.diskIO().execute { user_Dao.getUser(userId) }

        return when(user.value){
            null -> getUserFirestoreDb();
            else ->  user
        }

    }

    /*------------ Get User Firestore Database -------------*/
    private fun getUserFirestoreDb(): LiveData<User> {        Timber.e("YOOO")

        firestoreDb.collection("User").document(userId)
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful){


                        val userResult = it?.getResult()?.toObject(User::class.java)!!

                        appExecutors.diskIO().execute { user_Dao.insertUser(userResult) }
                        Timber.e(userResult.toString())

                    }
                    else{Timber.e("Error getting documents.") }
                }

        //appExecutors.diskIO().execute {user_Dao.getUser(userId)}

        //return user_Dao.getUser(userId)
        return user
    }







    /*------------ Get User Firestore Database -------------*/
    fun getUser() : LiveData<Profiledite_ViewModel_State> = getUserFirestore()

    /*-----*/

    private fun getUserFirestore(): LiveData<Profiledite_ViewModel_State> {
        firestoreDb.collection("User").document(userId)
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        val userResult = it.getResult()?.toObject(User::class.java)!!
                        profilState.value =
                                Profiledite_ViewModel_StateError(firstName = userResult.firstName, lastName = userResult.lastName, telNumber = userResult.tel_number,
                                        profilPic_url = userResult.profilPic)

                        user_Dao.insertUser(userResult)

                    }

                    else{profilState.value = Profiledite_ViewModel_StateError();Timber.e("Error getting documents.") }
                }
        return  profilState
    }




    /*----------------------- Update User ----------------------*/
    @SuppressLint("TimberArgCount")
    fun updateUser(firtname: String, lastame : String, phone : String, image_url : String ): LiveData<Profiledite_ViewModel_State> {

        val PHONE_PATTERN = Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")



        when{

            firtname.length!in(2..20) && lastame.length!in(2..20)&& !PHONE_PATTERN.matcher(phone).matches()-> profilState.value =
                    Profiledite_ViewModel_StateError(
                            firstName = firtname, lastName = lastame,telNumber = phone,
                            firstNameErrorVisiblity = View.VISIBLE, telNumberErrorVisiblity = View.VISIBLE, lastNameErrorVisiblity = View.VISIBLE)

            firtname.length!in(2..20) ->profilState.value =
                    Profiledite_ViewModel_StateError(firstName = firtname, lastName = lastame,telNumber = phone,firstNameErrorVisiblity = View.VISIBLE)

            lastame.length!in(2..20) ->profilState.value =
                    Profiledite_ViewModel_StateError(firstName = firtname, lastName = lastame,telNumber = phone, lastNameErrorVisiblity = View.VISIBLE)

            !PHONE_PATTERN.matcher(phone).matches() -> profilState.value =
                    Profiledite_ViewModel_StateError(firstName = firtname, lastName = lastame,telNumber = phone,telNumberErrorVisiblity = View.VISIBLE)


            else ->  firestoreDb.collection("user").document(userId)

                    .update("firstName", firtname, "lastName",lastame, "tel_number",phone, "profilPic",image_url)
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            Timber.d("DocumentSnapshot successfully updated!")

                            profilState.value = Profiledite_ViewModel_StateError(firstName = firtname, lastName = lastame, telNumber = phone, profilPic_url = image_url)
                        }

                        else{ Timber.w( "Error updating document") }
                    }
        }

        return  profilState
    }



}
