package com.amadoutirera.maakomome.domain

import android.content.Context
import com.amadoutirera.maakomome.utils_extension.networkInfo
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetworkHandler @Inject constructor(private val context: Context) {

    val isConnected get() = context.networkInfo?.isConnectedOrConnecting
}