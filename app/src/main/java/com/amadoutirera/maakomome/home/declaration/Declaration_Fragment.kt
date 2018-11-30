package com.amadoutirera.maakomome.home.declaration


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.dagger.ViewModelFactory
import com.amadoutirera.maakomome.utils.LiveConnectivityManager
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class Declaration_Fragment : Fragment() {
    private var isConnect : Boolean = false
    @Inject
    lateinit var viewModelFactory: ViewModelFactory



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.declaration_fragment, container, false)


        /*---------------- Check if user is connected -------------*/
        val liveConnectivityManager = LiveConnectivityManager(requireActivity())
        liveConnectivityManager.observe(this, Observer {isConnected -> isConnect = isConnected })


        /*---------------- Check if user is connected -------------*/


        /*---------------- Check if user is connected -------------*/



        /*---------------- Check if user is connected -------------*/



        /*---------------- Check if user is connected -------------*/




        return view
    }


    /*---------------- Dagger Injection -------------*/
    @Override
    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context);
    }


}
