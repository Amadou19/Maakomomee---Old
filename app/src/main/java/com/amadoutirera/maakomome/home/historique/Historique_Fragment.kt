package com.amadoutirera.maakomome.home.historique


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.amadoutirera.maakomome.R
import com.amadoutirera.maakomome.utils.LiveConnectivityManager
import com.amadoutirera.maakomome.Maakomome_Navigation_Activity
import com.amadoutirera.maakomome.dagger.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.historique_fragment.view.*
import javax.inject.Inject


class Historique_Fragment : Fragment() {
    private var isConnect : Boolean = false
    @Inject lateinit var viewModelFactory: ViewModelFactory


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.historique_fragment, container, false)


        /*---------------- Check if user is connected -------------*/
        val liveConnectivityManager = LiveConnectivityManager(requireActivity())
        liveConnectivityManager.observe(this, Observer {isConnected -> isConnect = isConnected })


        /*-- Replace Historique_Fragment with Declaration_Fragment--*/
        //view.declar_btn.setOnClickListener { view.findNavController().navigate(R.id.declaration_Fragment) }


        /*---- Replace Historique_Fragment with Profil_Fragment ---*/
        view.edite_profil_img_btn.setOnClickListener { view.findNavController().navigate(R.id.profil_Fragment) }


        /*---------------- Check if user is connected -------------*/




        view.declar_btn.setOnClickListener {
            startActivity(Intent(activity, Maakomome_Navigation_Activity::class.java))
            requireActivity().finish()
        }




        /*view.edite_profil_img_btn.setOnClickListener {
             val firestoreDb =  FirebaseFirestore.getInstance()
             val userId = FirebaseAuth.getInstance().currentUser!!.uid
            val listAffliatResult : MutableList<Affiliate>  = mutableListOf()

            Timber.e("getting documents...")
            firestoreDb.collection("User/$userId/Affiliate")
                    .get()
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            val listAffliatResult : MutableList<Affiliate>  = mutableListOf()

                            for (document : QueryDocumentSnapshot in it.getResult()) {
                                listAffliatResult.add(document.toObject(Affiliate::class.java))
                            }
                            context!!.toast(listAffliatResult.toString())

                            //val affliatResult = it.getResult().toObjects(Affiliate::class.java)!!
                           //context?.toast(affliatResult.toString())
                        }

                        else{Timber.e("Error getting documents.") }
                    }
        }*/









        /*---------------- Check if user is connected -------------*/

        Glide.with(this).load("https://i.stack.imgur.com/x8PhM.png").apply(RequestOptions.circleCropTransform()).into(view.userProfil_img);


        /*---------------- Check if user is connected -------------*/





        return  view
    }



    /*---------------- Dagger Injection -------------*/
    @Override
    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context);
    }


}
