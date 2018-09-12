package com.amadoutirera.maakomome.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.utils.toast
import com.amadoutirera.maakomome.view.authentication.Auth_Navigation_Activity
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.home_activity_viewpager.*


class Home_ViewPager : AppCompatActivity() {

    private lateinit var firebaseAuth : FirebaseAuth
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private val TAG = Home_ViewPager::class.java.simpleName



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity_viewpager)

        /*------------------------------------------------*/

        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        /*------------------------------------------------*/


        if (user != null && !user.isEmailVerified)this.toast(user.isEmailVerified.toString()+"  Hohé hoyé Vous n'avez pas validé votre email ...")
        this.toast(user?.isEmailVerified.toString())


        /*------------------------------------------------*/

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the two
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        /*------------------------------------------------*/
        fab.setOnClickListener {


        }//

    }

    /******************************************** Functions ***************************************/

    fun signOut() {

        firebaseAuth.signOut()
        toast("Déconexion...")
        startActivity(Intent(this, Auth_Navigation_Activity::class.java))
        finish()
    }

    /*------------------------------------------------*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_home__navigation_pager, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.action_settings) {
            signOut()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
    /*------------------------------------------------*/


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            var fragment : Fragment? = null

            when(position){
              0 ->    fragment = Fragment1()
              1 ->  fragment = Fragment2()
            }
            return fragment
        }

        override fun getCount(): Int {
            return 2
        }
    }

}
