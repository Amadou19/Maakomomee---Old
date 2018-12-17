package com.amadoutirera.maakomome.presentation.declaration


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class Declaration_Fragment : Fragment() {
    @Inject lateinit var viewModelFactory: ViewModelFactory



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val view =  inflater.inflate(R.layout.declaration_fragment, container, false)





        /*----------------  -------------*/
        /*----------------  -------------*/
        /*----------------  -------------*/
        /*----------------  -------------*/
        /*----------------  -------------*/
        /*----------------  -------------*/
        /*----------------  -------------*/







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
