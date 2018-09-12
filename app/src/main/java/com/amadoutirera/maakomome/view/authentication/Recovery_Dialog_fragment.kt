package com.amadoutirera.maakomome.view.authentication

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.utils.toast
import kotlinx.android.synthetic.main.recovery_dialog_fragment.*
import kotlinx.android.synthetic.main.sign_fragment.*
import java.util.regex.Pattern

class Recovery_Dialog_fragment : DialogFragment() {
    var interface_Aut_Dialog_fragmentListner : Interface_Aut_Dialog_fragment?  = null

    interface Interface_Aut_Dialog_fragment{
        fun positiveButtonClick()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        val builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater
        builder.setView(inflater?.inflate(R.layout.recovery_dialog_fragment, null))
                .setPositiveButton(getString(R.string.send), DialogInterface.OnClickListener { dialogInterface, id ->
                   interface_Aut_Dialog_fragmentListner?.positiveButtonClick()

                    if (!EMAIL_PATTERN.matcher(dialog.adresseEmailEdt.text.trim().toString()).matches()){
                        context?.toast(getString(R.string.email_error))
                    }


                })



        return  builder.create()
    }
}

