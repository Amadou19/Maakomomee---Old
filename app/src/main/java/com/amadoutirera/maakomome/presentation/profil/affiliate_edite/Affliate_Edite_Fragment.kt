package com.amadoutirera.maakomome.presentation.profil.affiliate_edite

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.amadoutirera.maakomome.R

class Affliate_Edite_Fragment : Fragment() {

    companion object {
        fun newInstance() = Affliate_Edite_Fragment()
    }

    private lateinit var viewModel: Affliate_Edite_ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.affliate_edite_fragment, container, false)


        //viewTransactionButton.setOnClickListener { view.findNavController().navigate(R.id.action_global_affliate_Edite_Fragment) }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Affliate_Edite_ViewModel::class.java)
        // TODO: Use the ViewModel

    }




}
