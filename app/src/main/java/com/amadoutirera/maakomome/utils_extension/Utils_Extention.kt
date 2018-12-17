package com.amadoutirera.maakomome.utils_extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.amadoutirera.maakomome.R
import com.google.android.material.snackbar.Snackbar





/*------------------ Extract Text --------------------------------*/
fun EditText.extractContent(extractType : Int): String {
    when(extractType){
        1 ->return text.toString()
        2 ->return text.trim().toString()
        3-> return text.trim().toString().toLowerCase()
        4 ->return text.trim().toString().toUpperCase()
    }
    return ""
}



/*------------------ Easy Toast --------------------------------*/
fun Context.toast(message: String) {
    Toast.makeText(this, message, (R.dimen.toastDuration as Int)).show()
}



/*------------------ Easy Snackbar --------------------------------*/
fun View.snackbar(message: String) {
    Snackbar .make(this, message, Snackbar.LENGTH_LONG).show()
}



/*------------------ Livedata onLine ------------------------*/
val Context.networkInfo: NetworkInfo? get() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo



