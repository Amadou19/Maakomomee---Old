package com.amadoutirera.maakomome.presentation.profil.profil_display

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amadoutirera.maakomome.base.Base_ViewHolder
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.model.Affiliate
import com.amadoutirera.maakomome.model.User


class Profil_Display_Adapter(private val profilListner: ProfilListner, private val data: List<Comparable<*>>): RecyclerView.Adapter<Base_ViewHolder<*>>() {


    //private val data: MutableList<Comparable<*>>

    companion object { private  val TYPE_USER_PROFIL = 1; private  val TYPE_AFFLIATE = 2 }

    //init { data = ArrayList() }



    /*---------  ----------*/

        fun setData(newData: List<Comparable<*>>) {
        //data.clear()
        //data.addAll(newData)
        data == newData
        notifyDataSetChanged()
    }


    /*---------  ----------*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Base_ViewHolder<*> {
        return when (viewType) {

            TYPE_USER_PROFIL -> {

                val view = LayoutInflater.from(parent.context).inflate(R.layout.profil_item, parent, false)
                User_Display_ViewHolder(view, profilListner, data as List<User>)
            }

            TYPE_AFFLIATE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.affliat_item, parent, false)
                Affliat_Display_ViewHolder(view, profilListner, data as List<Affiliate>)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    /*---------  ----------*/
    override fun getItemCount(): Int {
        return data.size
    }


    /*---------  ----------*/
    override fun getItemViewType(position: Int): Int {
        val comparable = data[position]
        return when (comparable) {
            is User -> TYPE_USER_PROFIL
            is Affiliate -> TYPE_AFFLIATE
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }


    /*---------  ----------*/
    override fun onBindViewHolder(holder: Base_ViewHolder<*>, position: Int) {
        val element = data[position]

        when(holder){
            is User_Display_ViewHolder -> holder.bind(element as User)
            is Affliat_Display_ViewHolder -> holder.bind(element as Affiliate)
            else -> throw IllegalArgumentException()
        }
    }




}