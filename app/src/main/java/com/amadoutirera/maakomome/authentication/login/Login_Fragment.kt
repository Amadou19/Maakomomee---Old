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
import com.amadoutirera.maakomome.authentication.login.Login_ViewModel
import com.amadoutirera.maakomome.dagger.ViewModelFactory
import com.amadoutirera.maakomome.utils.LiveConnectivityManager
import com.amadoutirera.maakomome.utils.snackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.login_fragment.view.*
import javax.inject.Inject


class Login_Fragment : Fragment() {
    private lateinit var login_ViewModel : Login_ViewModel
    private var isConnect : Boolean = false
    @Inject lateinit var viewModelFactory: ViewModelFactory



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.login_fragment, container, false)


        /*---------------- Check if user is connected -------------*/
        val liveConnectivityManager = LiveConnectivityManager(requireActivity())
        liveConnectivityManager.observe(this, Observer {isConnected -> isConnect = isConnected })


        /*-------- Replace Login_Fragment with Sign_Fragment-------*/
        view.creat_account.setOnClickListener { view.findNavController().navigate(R.id.sign_Fragment) }


        /*------ Replace Login_Fragment with Recovery_Fragment-----*/
        view.sign_btn.setOnClickListener { view.findNavController().navigate(R.id.recovery_Fragment) }


        /*---------------------------------------------------------*/
        view.login_btn.setOnClickListener{
            if (!isConnect){ view?.snackbar(getString(R.string.offLine)) ;return@setOnClickListener }

            view.progressBar1?.visibility = View.VISIBLE; view?.progressBar2?.visibility = View.VISIBLE
            login_ViewModel.login(email = view.emailTextInputLayout.text.trim().toString().toLowerCase(),password = view.password_Edt.text.toString())
        }


        /*------ Observe Sign_viewmodel changement an update UI -------*/
        login_ViewModel = ViewModelProviders.of(requireActivity()).get(Login_ViewModel::class.java)
        login_ViewModel.getState().observe(this, Observer { loginStat -> updateUi(loginStat!!) })

        return view
    }


    /*-------------------------------- Functions ------------------------*/


    private fun updateUi(state: Login_ViewModel_State) {
        return when(state){
            is Login_ViewModel_State_error -> {
                view?.login_error?.setText(state.emailOrPassworError)!!
                view?.login_error?.visibility = state.loginErrorVisiblity
                view?.progressBar1?.visibility = state.progressBarVisiblity; view?.progressBar2?.visibility = state.progressBarVisiblity
            }

            is Login_ViewModel_State_Success -> {
                view?.login_error?.setText(state.emailOrPassworError)!!
                view?.login_error?.visibility = state.loginErrorVisiblity
                view?.progressBar1?.visibility = state.progressBarVisiblity; view?.progressBar2?.visibility = state.progressBarVisiblity
                view?.findNavController()?.navigate(R.id.historique_Fragment)!!
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


