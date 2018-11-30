package com.amadoutirera.maakomome

import android.net.ConnectivityManager
import javax.inject.Inject
import javax.inject.Singleton

//class NetManager(private var applicationContext: Context) {
@Singleton
class NetManager @Inject constructor(private val connectivityManager: ConnectivityManager) {

    val isConnectedToInternet: Boolean?
        get() {
            //val conManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = connectivityManager.activeNetworkInfo
            return ni != null && ni.isConnected
        }
}