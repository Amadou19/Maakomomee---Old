package com.amadoutirera.maakomome.view.authentication


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amadoutirera.maakomome.Application

import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.utils.toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.recovery_fragment.view.*
import java.util.regex.Pattern


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Recovery_Fragment : Fragment() {

    private val EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    val TAG = Recovery_Fragment::class.java.simpleName


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.recovery_fragment, container, false)

        view.send_btn.setOnClickListener{

            if (!EMAIL_PATTERN.matcher(view.emailAdresse_Edt.text.trim().toString()).matches()){
                view.emailErrorTview.visibility = View.VISIBLE
            }

        }

        return view
    }



    fun passewordRenitialise(emailAdresse : String){

        Application().firebaseAuth.sendPasswordResetEmail(emailAdresse)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Email sent.")
                    }else Log.d(TAG, " Error ")
                }
    }


}
