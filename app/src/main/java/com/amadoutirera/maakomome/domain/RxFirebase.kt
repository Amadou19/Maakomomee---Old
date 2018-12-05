package com.amadoutirera.maakomome.domain

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import io.reactivex.*
import io.reactivex.annotations.NonNull

class RxFirebase {


    fun <T> getObservable(@NonNull task:Task<T>):Single<T> {

        return Single.create(object:SingleOnSubscribe<T> {
            override fun subscribe(emitter: SingleEmitter<T>) {
            }

        })
    }




}