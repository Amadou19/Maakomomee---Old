package com.amadoutirera.maakomome.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData

class LiveConnectivityManager(private val context:Context) : LiveData<Boolean>() {

    private val networkReciver = object : BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent?) {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            value = networkInfo != null && networkInfo.isConnected
        }
    }

    /*------------------------------------------------*/

    override fun onActive() {
        val filtre = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(networkReciver,filtre)
    }

    /*------------------------------------------------*/

    override fun onInactive() {
        context.unregisterReceiver(networkReciver)
    }


}