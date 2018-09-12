package com.amadoutirera.maakomome.view.authentication


import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.utils.isOnline
import com.amadoutirera.maakomome.utils.toast
import com.amadoutirera.maakomome.view.home.Home_ViewPager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.sign_fragment.*
import kotlinx.android.synthetic.main.sign_fragment.view.*
import java.util.regex.Pattern


class Sign_Fragment : Fragment() {


     lateinit var firebaseAuth : FirebaseAuth
    private val TAG = Sign_Fragment::class.java.simpleName



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.sign_fragment, container, false)

        /*---------------------------------------------------------*/

        firebaseAuth = FirebaseAuth.getInstance()

        /*---------------------------------------------------------*/

        view.emailAdresse_Edt.setText("amadoutirera@msn.com")
        view.password_Edt?.setText("dakareda")
        /*---------------------------------------------------------*/

        view.send_btn?.setOnClickListener {

            when (context?.isOnline()){

                false -> return@setOnClickListener

                true ->{

                    val email = view.emailAdresse_Edt.text.trim().toString()
                    val password = view.password_Edt.text.toString()
                    view.emailErrorTview.visibility = View.GONE
                    view.passewordErrorTview.visibility = View.GONE
                    progressBarVisiblity(1)

                    authVerif(email,password)
                }
            }
        }
        /*---------------------------------------------------------*/


        return view
    }


    /*********************************** Functions ************************************************/



    /*----------------------- Verification----------------------------------*/

    private fun authVerif(email: String, password: String){
        val EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")

        when{

            email.isEmpty() && password.isEmpty() -> context?.toast(getString(R.string.empty_error))

            !EMAIL_PATTERN.matcher(email).matches() -> {
                view?.emailErrorTview?.apply {
                    visibility = View.VISIBLE
                    setText(getString(R.string.email_error))
                }
                progressBarVisiblity(2)
            }

            password.length !in(7..20)-> {
                view?.passewordErrorTview?.visibility = View.VISIBLE
                progressBarVisiblity(2)
            }

            else -> creatUsers(email,password)
        }
    }


    /*----------------------- Creat User Acount --------------------------- -*/

    private fun creatUsers(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful){
                        emailErrorTview.visibility = View.VISIBLE
                        emailErrorTview.setText(getString(R.string.email_exist))
                        progressBarVisiblity(2)
                        return@addOnCompleteListener
                    }
                    else if(it.isSuccessful){
                        dialog(email)
                        progressBarVisiblity(2)
                    }
                }
    }

    /*----------------------- ProgressBar Visiblity ------------------------*/

    private fun progressBarVisiblity(visiblity : Int){
        when(visiblity){
            1 -> {
                view?.progressBar1?.visibility = View.VISIBLE
                view?.progressBar2?.visibility = View.VISIBLE
            }
            2->{
                progressBar1?.visibility = View.GONE
                progressBar2?.visibility = View.GONE
            }
        }
    }

    /*-----------------------  Dialog  ------------------------*/

    @SuppressLint("StringFormatInvalid")
    private fun dialog(email: String){
        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.setMessage(getString(R.string.sucssec_sign,email))
                ?.setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, id ->
                    startActivity(Intent(activity, Home_ViewPager::class.java))
                    activity?.finish()
                })
        val dialog = builder?.create()
        dialog?.setCancelable(false)
        dialog?.show()
    }


}
