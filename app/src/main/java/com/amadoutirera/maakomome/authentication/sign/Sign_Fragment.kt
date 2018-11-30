package com.amadoutirera.maakomome.view.authentication


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.authentication.sign.Sign_ViewModel
import com.amadoutirera.maakomome.dagger.ViewModelFactory
import com.amadoutirera.maakomome.utils.LiveConnectivityManager
import com.amadoutirera.maakomome.utils.extractContent
import com.amadoutirera.maakomome.utils.snackbar
import com.amadoutirera.maakomome.worker.authentication.Authentication_Worker
import com.jakewharton.rxbinding3.widget.textChanges
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.sign_fragment.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import java.util.regex.Pattern
import javax.inject.Inject


class Sign_Fragment : Fragment() {
    val EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private lateinit var sign_ViewModel : Sign_ViewModel
    private var isConnect : Boolean = false
    val compositeDisposable = CompositeDisposable()
    @Inject lateinit var viewModelFactory: ViewModelFactory



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.sign_fragment, container, false)


        /*----------------- Chek if user is connected ----------------*/
        val liveConnectivityManager = LiveConnectivityManager(requireContext())
        liveConnectivityManager.observe(this, Observer {isConnected -> isConnect = isConnected })


        /*---------- Chek User Befor Suscribe --------*/
        val emailObservale = view.emailAdresse_Edt.textChanges()
                .skipInitialValue()
                .map { EMAIL_PATTERN.matcher(it).matches() }
                .distinctUntilChanged()
                .map { matcher -> if (matcher){ view?.emailErrorTview?.visibility = View.INVISIBLE } else view?.emailErrorTview?.visibility = View.VISIBLE
                    return@map matcher
                }.subscribeOn(Schedulers.io()) .observeOn(AndroidSchedulers.mainThread())

        val passwordObservale = view.password_Edt.textChanges()
                .skipInitialValue()
                .map { it.length in(7..20) }
                .distinctUntilChanged()
                .map { matcher -> if (matcher){ view?.passewordErrorTview?.visibility = View.INVISIBLE } else view?.passewordErrorTview?.visibility = View.VISIBLE
                    return@map matcher
                }
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io()) .observeOn(AndroidSchedulers.mainThread())

        compositeDisposable += Observable.combineLatest(emailObservale,passwordObservale, object  : BiFunction<Boolean, Boolean, Boolean>{
            override fun apply(emailChek: Boolean, passwordCheck: Boolean): Boolean { return emailChek && passwordCheck }
        }).subscribeOn(Schedulers.io()) .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {it -> view.sign_btn.isEnabled = it}


        /*---------------------------- Creat User ---------------------*/
        view.sign_btn?.setOnClickListener {

            if (!isConnect){ view?.snackbar(getString(R.string.offLine)) ;return@setOnClickListener }
            view.progressBar1.visibility = View.VISIBLE; view.progressBar2.visibility = View.VISIBLE
            sign_ViewModel.creatUsers(view.emailAdresse_Edt.extractContent(3),view.password_Edt.extractContent(1))
        }


        /*------ Observe Sign_viewmodel changement an update UI -------*/
        sign_ViewModel = ViewModelProviders.of(this,viewModelFactory).get(Sign_ViewModel::class.java)
        sign_ViewModel.getState().observe(this, Observer {state -> updateUi(state, view.emailAdresse_Edt.extractContent(3)) })



        //requireContext().alert(R.string.sucssec_sign) { yesButton {} }.show()

        return view

    }



    private fun updateUi(state: Sign_ViewModel_State,adressEmail : String) {
        return when(state){

            is Sign_ViewModel_State_error ->{

                view?.progressBar1?.visibility = state.progressBarVisiblity; view?.progressBar2?.visibility = state.progressBarVisiblity
                view?.emailErrorTview?.visibility = state.emailErrorTviewVisiblity
                view?.passewordErrorTview?.visibility = state.passewordEtVisiblity
                view?.emailErrorTview?.text = state.emailErrorTview
                view?.passewordErrorTview?.text = state.passewordEtrrorTview

            }

            Sign_ViewModel_State_Success -> {
               /* view?.progressBar1?.visibility = state; view?.progressBar2?.visibility = state.progressBarVisiblity
                view?.emailErrorTview?.visibility = state.emailErrorTviewVisiblity
                view?.passewordErrorTview?.visibility = state.passewordEtVisiblity
                view?.emailErrorTview?.text = state.emailErrorTview
                view?.passewordErrorTview?.text = state.passewordEtrrorTview
                dialog_success(adressEmail)*/


            }
        }
    }


    /*---------------------------------------------------------*/

   /* @SuppressLint("StringFormatInvalid")
    private fun dialog_success(email: String){
        val builder = context?.let { AlertDialog.Builder(it) }
                ?.setMessage(getString(R.string.sucssec_sign,email))
                ?.setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, id ->
                    Authentication_Worker().sendEmailNow()
                    view?.findNavController()?.navigate(R.id.historique_Fragment)!!

                })
        val dialog = builder?.create()
        dialog?.setCancelable(false)
        dialog?.show()
    }*/

 @SuppressLint("StringFormatInvalid")
private fun dialog_success(email: String) {
     val builder = context?.let { AlertDialog.Builder(it) }
             ?.setMessage(getString(R.string.sucssec_sign, email))
             ?.setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, id ->
                 Authentication_Worker().sendEmailNow()
                 view?.findNavController()?.navigate(R.id.historique_Fragment)!!

             })
     val dialog = builder?.create()
     dialog?.setCancelable(false)
     dialog?.show()
 }

/*---------------- Dagger Injection -------------*/
@Override
override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context);
}


/*---------------- RX Disposable -------------*/
override fun onStop() {
    super.onStop()
    if(!compositeDisposable.isDisposed) compositeDisposable.dispose()

}


}
