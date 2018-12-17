package com.amadoutirera.maakomome.domain


import com.google.android.gms.tasks.Task
import io.reactivex.Single
import io.reactivex.annotations.NonNull

object RxFirebase {




    @NonNull
    fun <T> firebaseAuthObservable(@NonNull task: Task<T>): Single<Task<T>> {
        return Single.create { emitter ->
            if (!emitter.isDisposed) { emitter.onSuccess(task) }
        }
    }




}