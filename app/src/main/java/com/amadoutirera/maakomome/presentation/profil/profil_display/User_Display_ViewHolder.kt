package com.amadoutirera.maakomome.presentation.profil.profil_display

import android.view.View
import com.amadoutirera.maakomome.base.Base_ViewHolder

import com.amadoutirera.maakomome.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.profil_item.view.*

class User_Display_ViewHolder(val view : View, val profilListner : ProfilListner, val profilList : List<User> ): Base_ViewHolder<User>(view),View.OnClickListener {
    val userFirstnam_tv = view.userFirstnam_tv
    val userLastnam_tv = view.userLastnam_tv
    val userProfil_img = view.userProfil_img
    val userTelNumber_tv = view.userTelNumber_tv
    val userProfil_edite_img_btn = view.UserProfil_edite_img_btn

    override fun bind(userProfil: User) {
        userFirstnam_tv.text = userProfil.firstName
        userLastnam_tv.text = userProfil.lastName
        userTelNumber_tv.text = userProfil.tel_number
        Glide.with(view.context).load(userProfil.profilPic).apply(RequestOptions.circleCropTransform()).into(userProfil_img)
        userProfil_edite_img_btn.setOnClickListener(this)


    }


    /*--- Recyclerview  item OnclickListener for view type = UserProfil ---*/
    override fun onClick(v: View?) {
        val position = adapterPosition

        if (adapterPosition < 0) return

        val user = profilList[position]
        profilListner.userProfilListner(user, adapterPosition)
    }

}