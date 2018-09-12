package com.amadoutirera.maakomome.view.authentication

import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.utils.toast
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

data class User_Auth(val email : String, val passeword : String) {
    val firebaseAuth = FirebaseAuth.getInstance()

    enum class State { NEW, Active, CREATED }

    private val EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    var state: State? = null
    //var state: State = State.NEW


    init {
        signValidation(email, passeword)
    }


    fun signValidation(email: String, password: String) {

        require(email.trim().isNotEmpty() && password.isNotEmpty()) { "Veiller remplire tous les champs" }
        require(password.length >= 7) { "Le mot de passe doit etre superieur à 6 carractéres" }
        require(EMAIL_PATTERN.matcher(email).matches()) { "Adresse email incorrect" }
        state = State.CREATED

        /*---------------------------------------------------*/
        firebaseAuth.createUserWithEmailAndPassword(email, password)

                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                }
                .addOnFailureListener {
                    //toast(it.message.toString())
                }
    }


    /******************************************************************************/

    private fun Context.dialog(email: String){

        val emailEditext = EditText(this)
        with(emailEditext){
            inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            paddingLeft.and(12)
            hint = getString(R.string.email_adresse)
        }

        val builder = this?.let { AlertDialog.Builder(it) }
                ?.setTitle(getString(R.string.password_forgot))
                ?.setMessage(getString(R.string.password_forgot_message))
                ?.setView(emailEditext)
                ?.setPositiveButton(getString(R.string.send), DialogInterface.OnClickListener { dialogInterface, id ->
                })
        val dialog = builder?.create()
        dialog?.setCancelable(false)

        dialog?.show()
    }


}