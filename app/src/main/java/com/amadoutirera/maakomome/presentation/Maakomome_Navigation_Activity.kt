package com.amadoutirera.maakomome.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.amadoutirera.maakomome.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.maakomome_navigation_activity.*

class Maakomome_Navigation_Activity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.maakomome_navigation_activity)



        /*----------------  -------------*/
        val appBarConfiguration = AppBarConfiguration.Builder(setOf(R.id.login_Fragment, R.id.historique_Fragment)).build()
        NavigationUI.setupActionBarWithNavController(this, NavHostFragment.findNavController(maakomome_nav_host_fragment),appBarConfiguration)




    }



    override fun onSupportNavigateUp() = findNavController(R.id.maakomome_nav_host_fragment).navigateUp()

}
