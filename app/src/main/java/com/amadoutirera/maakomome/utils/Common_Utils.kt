package com.amadoutirera.maakomome.utils

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.view.home.Home_ViewPager


//************************** Easy Toast ********************************/

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

//************************** Get if user is onLine *********************/

fun Context.isOnline(): Boolean {
    val connectivityManager
            = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val networkInfo = connectivityManager!!.activeNetworkInfo
    if (networkInfo == null )toast(getString(R.string.isOnline))
    return networkInfo != null && networkInfo.isConnected
}

//************************** Easy Dialoge ********************************/


//**************************  ********************************/
//**************************  ********************************/
//**************************  ********************************/
//**************************  ********************************/
//**************************  ********************************/
//**************************  ********************************/
