package com.amadoutirera.maakomome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.amadoutirera.maakomome.utils.LiveConnectivityManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.maakomome_navigation_activity.*

class Maakomome_Navigation_Activity : AppCompatActivity() {
    private var isConnect : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.maakomome_navigation_activity)

        /*----------------  -------------*/
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        NavigationUI.setupActionBarWithNavController(this, NavHostFragment.findNavController(auth_nav_host_fragment))

        /*---------------- Check if user is connected -------------*/
        val liveConnectivityManager = LiveConnectivityManager(this)
        liveConnectivityManager.observe(this, Observer {isConnected -> isConnect = isConnected })


        /*----------------  -------------*/

    }


    override fun onSupportNavigateUp() = findNavController(R.id.auth_nav_host_fragment).navigateUp()

}
