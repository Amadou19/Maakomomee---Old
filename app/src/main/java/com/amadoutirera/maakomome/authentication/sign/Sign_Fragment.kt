package com.amadoutirera.maakomome.view.authentication


import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.room.SharedSQLiteStatement
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.authentication.sign.Sign_ViewModel
import com.amadoutirera.maakomome.dagger.ViewModelFactory
import com.amadoutirera.maakomome.utils.LiveConnectivityManager
import com.amadoutirera.maakomome.utils.extractContent
import com.amadoutirera.maakomome.utils.snackbar
import com.jakewharton.rxbinding3.widget.afterTextChangeEvents
import com.jakewharton.rxbinding3.widget.textChanges
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.sign_fragment.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import timber.log.Timber
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern
import javax.inject.Inject


class Sign_Fragment : Fragment() {
    private lateinit var sign_ViewModel : Sign_ViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.sign_fragment, container, false)



        /*---------------------------- Creat User ---------------------*/
        view.sign_btn?.setOnClickListener { sign_ViewModel.creatUsers(view.emailAdresse_Edt.extractContent(3),view.password_Edt.extractContent(1)) }
       // view.sign_btn?.setOnClickListener { sign_ViewModel.creatUsers("amadoutirera@msn.com","dakareda") }
        view?.emailAdresse_Edt.setText("amadoutirera@msn.")
        view?.password_Edt.setText( "dakareda")


        /*------ Observe Sign_viewmodel changement an update UI -------*/
        sign_ViewModel = ViewModelProviders.of(this,viewModelFactory).get(Sign_ViewModel::class.java)
        sign_ViewModel.getState().observe(this, Observer {state -> updateUi(state!!) })



        return view
    }


    /*----------------  Update UI  -------------*/
    private fun updateUi(state: Sign_ViewModel.Sign_ViewModel_State) {

        return when(state){

            is Sign_ViewModel.Sign_ViewModel_State.Editable_State -> {

                view?.emailErrorTview?.text = state.emailErrorTview?.let { getString(it) }

                view?.passewordErrorTview?.text = state.passewordErrorTview?.let { getString(it) }

                if (state.emailErrorTviewVisiblity) view?.emailErrorTview?.visibility = View.VISIBLE else  view?.emailErrorTview?.visibility = View.INVISIBLE

                if (state.passewordErrortVisiblity) view?.passewordErrorTview?.visibility  = View.VISIBLE else view?.passewordErrorTview?.visibility  = View.INVISIBLE

                if (state.progressBarVisiblity) view?.progressBar1?.visibility  = View.VISIBLE else  view?.progressBar1?.visibility  = View.INVISIBLE

                if (state.progressBarVisiblity) view?.progressBar2?.visibility  = View.VISIBLE else view?.progressBar2?.visibility  = View.INVISIBLE
            }

            Sign_ViewModel.Sign_ViewModel_State.State_Success -> {

                requireContext().alert(R.string.sucssec_sign) {
                    yesButton { view?.findNavController()?.navigate(R.id.historique_Fragment);it.dismiss()  }
                    ; isCancelable = false
                }.show()

                view?.emailErrorTview?.text = ""
                view?.passewordErrorTview?.text = ""
                view?.emailErrorTview?.visibility = View.INVISIBLE
                view?.passewordErrorTview?.visibility  = View.INVISIBLE
                view?.progressBar1?.visibility  = View.INVISIBLE
                view?.progressBar2?.visibility  = View.INVISIBLE

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
