package com.amadoutirera.maakomome.presentation.profil.User_edite


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
import com.amadoutirera.maakomome.model.User
import com.amadoutirera.maakomome.utils_extension.extractContent
import com.amadoutirera.maakomome.utils_extension.toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.profil_edite_fragment.view.*


class Profil_Edite_Fragment : Fragment() {
    private lateinit var profileditViewModel : Profil_Edite_ViewModel
    private val userProfilEdite = MutableLiveData<User>()



    @SuppressLint("RestrictedApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.profil_edite_fragment, container, false)




        /*---------------------- Update User  -----------------------*/
        view.edite_profil_fb_btn.setOnClickListener {

        }


        /*--- Observe Profil_Edite_ViewModel ande Update UI if changed -*/
        profileditViewModel = ViewModelProviders.of(requireActivity()).get(Profil_Edite_ViewModel::class.java)
        //profileditViewModel.getprofiledite_Update_State().observe(this, Observer { profiledite_State -> updateUi(profiledite_State!!)})


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


        //profileditViewModel.getUser(FirebaseAuth.getInstance().currentUser?.uid!!)



        return view
    }

    private fun getuser() {

        //profileditViewModel.getProfile(true).observe(this, Observer { profiledite_State -> updateUi(profiledite_State!!)})

    }


    private fun updateUi(profilUserState : Profil_Edite_ViewModel.Profiledite_ViewModel_State) {
        return when(profilUserState){

            is Profil_Edite_ViewModel.Profiledite_ViewModel_State.Profiledite_ViewModel_StateError -> {
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
