package com.amadoutirera.maakomome.presentation.authentication.register


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.di.ViewModelFactory
import com.amadoutirera.maakomome.utils_extension.extractContent
import com.amadoutirera.maakomome.utils_extension.snackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.sign_fragment.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

import javax.inject.Inject


class Register_Fragment : Fragment() {
    private lateinit var register_ViewModel : Register_ViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.sign_fragment, container, false)




        /*---------------------------- Creat User ---------------------*/
        view.sign_btn?.setOnClickListener {
            register_ViewModel.creatUsers(view.emailAdresse_Edt.extractContent(3),view.password_Edt.extractContent(1))
        }


        /*------ Observe Sign_viewmodel changement an update UI -------*/
        register_ViewModel = ViewModelProviders.of(this,viewModelFactory).get(Register_ViewModel::class.java)
        register_ViewModel.getState().observe(this, Observer { state -> updateUi(state!!) })



        return view
    }



    /*----------------  Update UI  -------------*/
    private fun updateUi(state: Register_ViewModel.Sign_ViewModel_State) {

        return when(state){

            is Register_ViewModel.Sign_ViewModel_State.Editable -> {

                view?.emailErrorTview?.text = state.emailErrorTview?.let { getString(it) }

                view?.passewordErrorTview?.text = state.passewordErrorTview?.let { getString(it) }

                state.snackbarMessage?.getContentIfNotHandled()?.let { view?.snackbar(getString(state.snackbarMessage.peekContent()))  }

                if (state.emailErrorTviewVisiblity) view?.emailErrorTview?.visibility = View.VISIBLE else  view?.emailErrorTview?.visibility = View.INVISIBLE

                if (state.passewordErrortVisiblity) view?.passewordErrorTview?.visibility  = View.VISIBLE else view?.passewordErrorTview?.visibility  = View.INVISIBLE

                if (state.progressBarVisiblity) view?.progressBar1?.visibility  = View.VISIBLE else  view?.progressBar1?.visibility  = View.INVISIBLE

                if (state.progressBarVisiblity) view?.progressBar2?.visibility  = View.VISIBLE else view?.progressBar2?.visibility  = View.INVISIBLE
            }


            is Register_ViewModel.Sign_ViewModel_State.Success -> {

                view?.emailErrorTview?.text = ""
                view?.passewordErrorTview?.text = ""
                view?.emailErrorTview?.visibility = View.INVISIBLE
                view?.passewordErrorTview?.visibility  = View.INVISIBLE
                view?.progressBar1?.visibility  = View.INVISIBLE
                view?.progressBar2?.visibility  = View.INVISIBLE

                state.dialogSuccsessMessage?.getContentIfNotHandled()?.let {

                    requireContext().alert(R.string.sucssec_sign) {
                        yesButton { view?.findNavController()?.navigate(R.id.historique_Fragment); it.dismiss()  }
                        isCancelable = false
                    }.show()
                }

                val navOptions = NavOptions.Builder().setPopUpTo(R.id.login_Fragment, true).build()
                findNavController().navigate(R.id.historique_Fragment, null, navOptions)
            }
        }

    }




    /*---------------- Dagger Injection -------------*/
    @Override
    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context);
    }


}
