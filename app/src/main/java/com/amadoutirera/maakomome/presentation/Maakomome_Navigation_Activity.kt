package com.amadoutirera.maakomome.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.amadoutirera.maakomome.R
import com.google.android.material.navigation.NavigationView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.maakomome_navigation_activity.*

class Maakomome_Navigation_Activity : AppCompatActivity() {
    private lateinit var appBarConfiguration : AppBarConfiguration




    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.maakomome_navigation_activity)


        /*----------------  -------------*/
        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.maakomome_nav_host_fragment) as NavHostFragment? ?: return

        val navController = host.navController

        appBarConfiguration = AppBarConfiguration(setOf(R.id.login_Fragment, R.id.historique_Fragment))

        setupActionBarWithNavController(navController, appBarConfiguration)

    }






    override fun onSupportNavigateUp()=
            findNavController(R.id.maakomome_nav_host_fragment).navigateUp(appBarConfiguration)


}
