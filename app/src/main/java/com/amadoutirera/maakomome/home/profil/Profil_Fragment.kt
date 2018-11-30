package com.amadoutirera.maakomome.home.profil


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.dagger.ViewModelFactory
import com.amadoutirera.maakomome.database.UserAffiliate_Dao
import com.amadoutirera.maakomome.database.User_Dao
import com.amadoutirera.maakomome.home.profil.profil_display.ProfilListner
import com.amadoutirera.maakomome.home.profil.profil_display.Profil_Display_Adapter
import com.amadoutirera.maakomome.home.profil.profil_display.Profil_Display_Viewmodel
import com.amadoutirera.maakomome.model.Affiliate
import com.amadoutirera.maakomome.model.User
import com.amadoutirera.maakomome.utils.LiveConnectivityManager
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.profil_fragment.view.*
import java.util.*
import java.util.concurrent.Executors
import javax.inject.Inject


class Profil_Fragment : Fragment(), ProfilListner {
    private lateinit var profil_Display_Adapter: Profil_Display_Adapter
    private var isConnect : Boolean = false
    var profilCombineList:MutableList<Comparable<*>> = ArrayList()
    private lateinit var profil_Display_Viewmodel : Profil_Display_Viewmodel
    @Inject lateinit var viewModelFactory: ViewModelFactory

    @Inject lateinit var user_dao : User_Dao
    @Inject lateinit var userAffiliate_Dao : UserAffiliate_Dao






    @SuppressLint("WrongConstant")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.profil_fragment, container, false)


        /*---------------- Check if user is connected -------------*/
        val liveConnectivityManager = LiveConnectivityManager(requireContext())
        liveConnectivityManager.observe(this, Observer {isConnected -> isConnect = isConnected })
        //if (!isConnect){ view?.snackbar(getString(R.string.offLine)) ;return@setOnClickListener }



        /*---------------- RecyclerView -------------*/
        profil_Display_Adapter = Profil_Display_Adapter(this,profilCombineList)
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




        Handler().postDelayed({
            val user = User(id = "e5VkPIc8SThXaZOwQC2xB1QmNqg1", id_mk = "9875456GFGHJH", firstName = "Sunadé Hokagé", lastName = "San", tel_number = "8765444444567", profilPic = "https://i.stack.imgur.com/hDGd1.jpg")
            val affiliatee = arrayListOf<Affiliate>()
            affiliatee.add(Affiliate(id = "e5VkPIc8SThXaZOwQC2xB1QmNqg1",userId = "e5VkPIc8SThXaZOwQC2xB1QmNqg1",fullName = "Konohamaru - Dono",tel_number = "7654345678"))
            affiliatee.add(Affiliate(id = "e5VkPIc8SThXaZOwQC2xB1QmNqg2",userId = "e5VkPIc8SThXaZOwQC2xB1QmNqg1",fullName = "Ibi sensé - Dono",tel_number = "7654345678"))
            Executors.newSingleThreadExecutor().execute { userAffiliate_Dao.insertUserAffiliate(affiliatee);user_dao.insertUser(user) }

        }, 10000 // value in milliseconds
        )


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
