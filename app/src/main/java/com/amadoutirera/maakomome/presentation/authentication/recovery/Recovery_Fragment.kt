package com.amadoutirera.maakomome.presentation.authentication.recovery


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.di.ViewModelFactory
import com.amadoutirera.maakomome.utils_extension.snackbar
import com.amadoutirera.maakomome.utils_extension.extractContent
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.recovery_fragment.view.*
import javax.inject.Inject



class Recovery_Fragment : Fragment() {
    private lateinit var recoveryViewModel : Recovery_ViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.recovery_fragment, container, false)


        /*----------------------------------------------------------*/
        view.sign_btn.setOnClickListener{
            recoveryViewModel.passewordRenitialise(view.emailAdresse_inp.extractContent(3))
        }


        /*----- Observe Recovery_viewmodel changement an update UI ---*/
        recoveryViewModel = ViewModelProviders.of(this,viewModelFactory).get(Recovery_ViewModel::class.java)
        recoveryViewModel.getState().observe(this, Observer { state -> updateUi(state!!) })



        return view
    }



    /*-----------------     Update UI Function       ----------------*/
    private fun updateUi(state: Recovery_ViewModel.Recovery_ViewModel_State) {

        return when(state){

            is Recovery_ViewModel.Recovery_ViewModel_State.Editable -> {

                view?.emailErrorTview?.text = state.tv_Error?.let { getString(it) }
                if (state.tv_ErrorVisiblity) view?.emailErrorTview?.visibility  = View.VISIBLE else  view?.emailErrorTview?.visibility  = View.INVISIBLE
                if (state.progressBarVisiblity) view?.progressBar1?.visibility  = View.VISIBLE else  view?.progressBar1?.visibility  = View.INVISIBLE
                if (state.progressBarVisiblity) view?.progressBar2?.visibility  = View.VISIBLE else  view?.progressBar2?.visibility  = View.INVISIBLE

                state.snackbarMessage?.getContentIfNotHandled()?.let { view?.snackbar(getString(state.snackbarMessage.peekContent()))  }!!



            }

            is Recovery_ViewModel.Recovery_ViewModel_State.Success -> {

                view?.emailErrorTview?.text = ""
                view?.emailErrorTview?.visibility  = View.INVISIBLE
                view?.progressBar1?.visibility  = View.INVISIBLE
                view?.progressBar2?.visibility  = View.INVISIBLE
                state.snackbarMessage?.let { getString(it) }?.let { view?.snackbar(it) }

                view!!.findNavController().navigate(R.id.login_Fragment)
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
