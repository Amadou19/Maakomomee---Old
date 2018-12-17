package com.amadoutirera.maakomome.presentation.authentication.login


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.di.ViewModelFactory
import com.amadoutirera.maakomome.utils_extension.snackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.login_fragment.view.*
import javax.inject.Inject


class Login_Fragment : Fragment() {
    private lateinit var login_ViewModel : Login_ViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {        setHasOptionsMenu(false)
        val view : View = inflater.inflate(R.layout.login_fragment, container, false)



        /*-------- Replace Login_Fragment with Register_Fragment-------*/
        view.creat_account.setOnClickListener { view.findNavController().navigate(R.id.sign_Fragment) }



        /*------ Replace Login_Fragment with Recovery_Fragment-----*/
        view.sign_btn.setOnClickListener { view.findNavController().navigate(R.id.recovery_Fragment) }



        /*------------------            ---------------------------*/
        view.login_btn.setOnClickListener{
            login_ViewModel.login(email = view.emailAdresse_edt.text.trim().toString().toLowerCase(),password = view.password_Edt.text.toString())
        }



        /*------ Observe Sign_viewmodel changement and update UI -------*/
        login_ViewModel = ViewModelProviders.of(this, viewModelFactory).get(Login_ViewModel::class.java)
        login_ViewModel.getState().observe(this, Observer { loginStat -> updateUi(loginStat!!) })




        return view
    }




    /*-------------------------------- Update UI ------------------------*/
    private fun updateUi(state: Login_ViewModel.Login_ViewModel_State) {
        return when(state){

            is Login_ViewModel.Login_ViewModel_State.Editable -> {

                state.snackbarMessage?.getContentIfNotHandled()?.let { view?.snackbar(getString(state.snackbarMessage.peekContent()))  }
                view?.login_errorTv?.text = state.emailAndPassworError?.let { getString(it) }

                if (state.btnIsEnabled) view?.login_btn?.isEnabled  = true else  view?.login_btn?.isEnabled = false
                if (state.loginErrorVisiblity) view?.login_errorTv?.visibility  = View.VISIBLE else  view?.login_errorTv?.visibility  = View.INVISIBLE
                if (state.progressBarVisiblity) view?.progressBar1?.visibility  = View.VISIBLE else  view?.progressBar1?.visibility  = View.INVISIBLE
                if (state.progressBarVisiblity) view?.progressBar2?.visibility  = View.VISIBLE else  view?.progressBar2?.visibility  = View.INVISIBLE }


            is Login_ViewModel.Login_ViewModel_State.Success  -> {
                view?.login_errorTv?.text = ""

                view?.login_errorTv?.visibility = View.INVISIBLE
                view?.progressBar1?.visibility = View.INVISIBLE
                view?.progressBar2?.visibility = View.INVISIBLE

                val navOptions = NavOptions.Builder().setPopUpTo(R.id.login_Fragment, true).build()
                findNavController().navigate(R.id.historique_Fragment, null, navOptions) }
        }

    }


    /*---------------- Dagger Injection -------------*/
    @Override
    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context);
    }


}


