package com.amadoutirera.maakomome.home.profil.User_edite


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.home.profil.User_edite.viewmodel.Profil_Edite_ViewModel
import com.amadoutirera.maakomome.home.profil.User_edite.viewmodel.Profiledite_ViewModel_State
import com.amadoutirera.maakomome.home.profil.User_edite.viewmodel.Profiledite_ViewModel_StateError
import com.amadoutirera.maakomome.model.User
import com.amadoutirera.maakomome.utils.LiveConnectivityManager
import com.amadoutirera.maakomome.utils.extractContent
import com.amadoutirera.maakomome.utils.snackbar
import com.amadoutirera.maakomome.utils.toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.profil_edite_fragment.view.*


class Profil_Edite_Fragment : Fragment() {
    private var isConnect : Boolean = false
    private lateinit var profileditViewModel : Profil_Edite_ViewModel
    private val userProfilEdite = MutableLiveData<User>()



    @SuppressLint("RestrictedApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.profil_edite_fragment, container, false)

        /*---------------- Check if user is connected -------------*/
        val liveConnectivityManager = LiveConnectivityManager(activity!!)
        liveConnectivityManager.observe(this, Observer {isConnected -> isConnect = isConnected })


        /*---------------------- Update User  -----------------------*/
        view.edite_profil_fb_btn.setOnClickListener {
            if (!isConnect){ view?.snackbar(getString(R.string.offLine)) ;return@setOnClickListener }

            val userProfiEdite = User(firstName = view.firtname_edt.extractContent(2), lastName = view.lastame_edt.extractContent(2),
                    tel_number = view.phone_edt.extractContent(2), profilPic = "https://66.media.tumblr.com/6e054c9201f0456914e7c88d2cd9f455/tumblr_nn9nvlhIZX1t4ic53o1_640.jpg")

            profileditViewModel.updteUser(userProfiEdite)
        }


        /*--- Observe Profil_Edite_ViewModel ande Update UI if changed -*/
        profileditViewModel = ViewModelProviders.of(requireActivity()).get(Profil_Edite_ViewModel::class.java)
        profileditViewModel.getprofiledite_Update_State().observe(this, Observer { profiledite_State -> updateUi(profiledite_State!!)})


        /*------------ function called once --------------*/
        getuser()


        /*----------------------- U-----------------*/
        userProfilEdite.observe(this, Observer {userParams ->

            context?.toast(userParams.firstName)

            Glide.with(requireContext()).load(userParams.profilPic).apply(RequestOptions.circleCropTransform()).into(view!!.userProfil_img)
            view.firtname_edt?.setText(userParams.firstName)
            view.lastame_edt?.setText(userParams.lastName)
            view.phone_edt?.setText(userParams.tel_number)

            context?.toast(userParams.firstName)

        })


        profileditViewModel.getUser(FirebaseAuth.getInstance().currentUser?.uid!!)



        return view
    }

    private fun getuser() {
        if (!isConnect){ view?.snackbar(getString(R.string.offLine)) ;return }

        profileditViewModel.getProfile(true).observe(this, Observer { profiledite_State -> updateUi(profiledite_State!!)})

    }


    private fun updateUi(profilUserState : Profiledite_ViewModel_State) {
        return when(profilUserState){

            is Profiledite_ViewModel_StateError -> {
                view?.firtname_tvError?.visibility = profilUserState.firstNameErrorVisiblity
                view?.lastname_tvError?.visibility = profilUserState.lastNameErrorVisiblity
                view?.phon_tvError?.visibility = profilUserState.telNumberErrorVisiblity
                val  userProfil = User(firstName = profilUserState.firstName,lastName = profilUserState.lastName,
                        tel_number = profilUserState.telNumber,profilPic = profilUserState.profilPic_url)
                userProfilEdite.value = userProfil
            }
        }
    }





}
