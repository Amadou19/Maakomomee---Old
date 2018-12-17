package com.amadoutirera.maakomome.presentation.historique


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.di.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.historique_fragment.view.*
import javax.inject.Inject


class Historique_Fragment : Fragment() {
    @Inject lateinit var viewModelFactory: ViewModelFactory


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val view =  inflater.inflate(R.layout.historique_fragment, container, false)




        /*-- Replace Historique_Fragment with Declaration_Fragment--*/
        view.declar_btn.setOnClickListener { view.findNavController().navigate(R.id.declaration_Fragment) }


        /*---- Replace Historique_Fragment with Profil_Fragment ---*/
        view.edite_profil_img_btn.setOnClickListener { view.findNavController().navigate(R.id.profil_Fragment) }



        //------- test
        Glide.with(this).load("https://i.stack.imgur.com/x8PhM.png").apply(RequestOptions.circleCropTransform()).into(view.userProfil_img);





        return  view
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
