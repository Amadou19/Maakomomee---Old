package com.amadoutirera.maakomome.view.authentication


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
import com.amadoutirera.maakomome.authentication.recovery.RecoveryViewmodel
import com.amadoutirera.maakomome.dagger.ViewModelFactory
import com.amadoutirera.maakomome.utils.LiveConnectivityManager
import com.amadoutirera.maakomome.utils.snackbar
import com.amadoutirera.maakomome.utils.extractContent
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.recovery_fragment.view.*
import javax.inject.Inject


class Recovery_Fragment : Fragment() {
    private lateinit var recoveryViewmodel : RecoveryViewmodel
    var isConnect : Boolean = false
    @Inject lateinit var viewModelFactory: ViewModelFactory



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.recovery_fragment, container, false)

        /*----------------- Chek if user is connected ----------------*/
        val liveConnectivityManager = LiveConnectivityManager(requireActivity())
        liveConnectivityManager.observe(requireActivity(), Observer {isConnected -> isConnect = isConnected })


        /*----------------------------------------------------------*/
        view.sign_btn.setOnClickListener{
            if (!isConnect){ view?.snackbar(getString(R.string.offLine)) ;return@setOnClickListener }
            view?.progressBar1?.visibility = View.VISIBLE; view?.progressBar2?.visibility = View.VISIBLE
            recoveryViewmodel.passewordRenitialise(view.emailTextInputLayout.extractContent(3))
        }


        /*----- Observe Recovery_viewmodel changement an update UI ---*/
        recoveryViewmodel = ViewModelProviders.of(this,viewModelFactory).get(RecoveryViewmodel::class.java)
        recoveryViewmodel.getState().observe(this, Observer {state -> updateUi(state!!) })


        return view
    }


    private fun updateUi(state: Recovery_ViewModel_State) {
        return when(state){

            is Recovery_ViewModel_State_error -> {
                view?.emailErrorTview?.visibility = state.stateText_errorVisiblity
                view?.emailErrorTview?.text = state.stateText_error
                view?.progressBar1?.visibility = state.progressBarVisiblity; view?.progressBar2?.visibility = state.progressBarVisiblity
            }

            is Recovery_ViewModel_State_Success ->{
                view?.emailErrorTview?.visibility = state.stateText_errorVisiblity
                view?.progressBar1?.visibility = state.progressBarVisiblity; view?.progressBar2?.visibility = state.progressBarVisiblity
                view?.emailErrorTview?.text = state.stateText_error
                view?.snackbar(state.snackbarMessage)!!
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
