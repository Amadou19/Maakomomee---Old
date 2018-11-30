package com.amadoutirera.maakomome.home.profil.profil_display

import com.amadoutirera.maakomome.model.Affiliate
import com.amadoutirera.maakomome.model.User

interface ProfilListner {

    fun userProfilListner(user: User, position: Int)

    fun affiliateListner(affiliate: Affiliate, positio: Int)
}