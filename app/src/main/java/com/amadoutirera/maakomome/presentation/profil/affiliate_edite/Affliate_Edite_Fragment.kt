package com.amadoutirera.maakomome.presentation.profil.affiliate_edite

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class Affliate_Edite_Fragment : Fragment() {

    private lateinit var viewModel: Affliate_Edite_ViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.affliate_edite_fragment, container, false)


        viewModel = ViewModelProviders.of(this,viewModelFactory).get(Affliate_Edite_ViewModel::class.java)





        return view
    }









    /*----------------       Menu      -------------*/
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_activity_navigation, menu)
    }




    /*---------------- Dagger Injection -------------*/
    @Override
    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context);
    }



}
