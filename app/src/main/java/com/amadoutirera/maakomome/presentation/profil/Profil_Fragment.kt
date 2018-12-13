package com.amadoutirera.maakomome.presentation.profil


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.di.ViewModelFactory
import com.amadoutirera.maakomome.presentation.profil.profil_display.ProfilListner
import com.amadoutirera.maakomome.presentation.profil.profil_display.Profil_Display_Adapter
import com.amadoutirera.maakomome.presentation.profil.profil_display.Profil_Display_Viewmodel
import com.amadoutirera.maakomome.model.Affiliate
import com.amadoutirera.maakomome.model.User
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.profil_fragment.view.*
import java.util.*
import javax.inject.Inject


class Profil_Fragment : Fragment(), ProfilListner {
    private lateinit var profil_Display_Adapter: Profil_Display_Adapter
    var profilCombineList:MutableList<Comparable<*>> = ArrayList()
    private lateinit var profil_Display_Viewmodel : Profil_Display_Viewmodel
    @Inject lateinit var viewModelFactory: ViewModelFactory



    @SuppressLint("WrongConstant")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.profil_fragment, container, false)



        /*---------------- RecyclerView -------------*/
        profil_Display_Adapter = Profil_Display_Adapter(this, profilCombineList)
        view.profil_recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = profil_Display_Adapter
        }



        /*---------------- Observe profil_Display_Viewmodel and update ui -------------*/
        profil_Display_Viewmodel = ViewModelProviders.of(this,viewModelFactory).get(Profil_Display_Viewmodel::class.java)
        profil_Display_Viewmodel.getUserProfil().observe(this, Observer {profil ->

            profilCombineList.clear()
            profilCombineList.addAll(profil as List<Comparable<*>>)
            profil_Display_Adapter.setData(profilCombineList)
        })




        return view
    }



    /*----------------  -------------*/
    override fun userProfilListner(user: User, position: Int) {}


    /*----------------  -------------*/
    override fun affiliateListner(affiliate: Affiliate, positio: Int) {}



    /*---------------- Dagger Injection -------------*/
    @Override
    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context);
    }



}
