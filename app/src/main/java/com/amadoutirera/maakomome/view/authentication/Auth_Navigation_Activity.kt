package com.amadoutirera.maakomome.view.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.view.home.Home_ViewPager
import com.google.firebase.auth.FirebaseAuth

class Auth_Navigation_Activity : AppCompatActivity() {

    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*---------------------------------------------------------*/
        firebaseAuth = FirebaseAuth.getInstance()
        /*---------------------------------------------------------*/

    }




    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

    /*------------------------------------------------------------------------*/

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.getCurrentUser() != null){
            startActivity(Intent(this, Home_ViewPager::class.java))
            finish()
        }
    }


}
