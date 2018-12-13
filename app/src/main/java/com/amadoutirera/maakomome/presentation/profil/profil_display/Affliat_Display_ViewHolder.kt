package com.amadoutirera.maakomome.presentation.profil.profil_display

import android.view.View
import com.amadoutirera.maakomome.base.Base_ViewHolder
import com.amadoutirera.maakomome.model.Affiliate
import kotlinx.android.synthetic.main.affliat_item.view.*

class Affliat_Display_ViewHolder(val view: View, val listener : ProfilListner, val affliat_Display : List<Affiliate>) : Base_ViewHolder<Affiliate>(view), View.OnClickListener {
    val affiliate_fullName_tv = view.affiliate_fullName_tv
    val affiliate_tel_number_tv = view.affiliate_tel_number_tv


    override fun bind(affiliate: Affiliate) {
        affiliate_fullName_tv.text = affiliate.fullName
        affiliate_tel_number_tv.text = affiliate.tel_number

    }


    /*--- Recyclerview  item OnclickListener for view type = Affiliate ---*/
    override fun onClick(v: View?) {
        if (adapterPosition < 0) return
        listener.affiliateListner(affliat_Display[adapterPosition], adapterPosition)
    }


}