package com.amadoutirera.maakomome.view.authentication


import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.utils.isOnline
import com.amadoutirera.maakomome.utils.toast
import com.amadoutirera.maakomome.view.home.Home_ViewPager
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_fragment.view.*


class Login_Fragment : Fragment() {

    private lateinit var firebaseAuth : FirebaseAuth
    private val TAG: String = Login_Fragment::class.java.simpleName


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.login_fragment, container, false)

        /*---------------------------------------------------------*/

        firebaseAuth = FirebaseAuth.getInstance()

        /*---------------------------------------------------------*/

        //val args = Login_FragmentArgs.fromBundle(arguments).emailAdresse

        /*---------------------------------------------------------*/

        view?.emailAdresse_Edt.setText("amadoutirera@msn.com")
        view.password_Edt.setText("dakareda")

        /*---------------------------------------------------------*/

        view.login_btn.setOnClickListener {
            if (!context?.isOnline()!!)return@setOnClickListener
            visiblity(1)
            login( view.emailAdresse_Edt.text.trim().toString(),view.password_Edt.text.toString())
        }

        /*---------------------------------------------------------*/

        //Replace Login_Fragment with Sign_Fragment
        view.creat_account.setOnClickListener {
            view.findNavController().navigate(R.id.sign_Fragment)
        }

        /*---------------------------------------------------------*/

        //Passeword Recovery
        view.recovery_btn.setOnClickListener {
            view.findNavController().navigate(R.id.recovery_Fragment)
        }



        return view;
    }

    /*-------------------------------- Functions -------------------------------------------------*/

    //login
    private fun login(email: String, password: String) {

        when {
            email.isEmpty() || password.isEmpty() -> {
                view?.login_error?.setText(getString(R.string.empty_error))
                visiblity(2)
            }
            else -> { firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                            if (it.isSuccessful) starHomeActivity() //
                            else {view?.login_error?.setText(getString(R.string.email_or_passeword_error))
                                visiblity(2)
                                return@addOnCompleteListener
                            }
                        }
            }
        }
    }

    /*------------------------------------------------------------------------*/
    private fun visiblity(visiblity : Int){
        when(visiblity){
            1 -> {
                view?.progressBar1?.visibility = View.VISIBLE
                view?.progressBar2?.visibility = View.VISIBLE
                view?.login_error?.visibility = View.GONE
                view?.login_btn?.isClickable = false
            }
            2 -> {
                view?.progressBar1?.visibility = View.GONE
                view?.progressBar2?.visibility = View.GONE
                view?.login_error?.visibility = View.VISIBLE
                view?.login_btn?.isClickable = true

            }
        }
    }
    /*------------------------------------------------------------------------*/
    private fun starHomeActivity() {
        if (firebaseAuth.getCurrentUser() != null){
            startActivity(Intent(context, Home_ViewPager::class.java))
            activity!!.finish()
        }
    }



}


