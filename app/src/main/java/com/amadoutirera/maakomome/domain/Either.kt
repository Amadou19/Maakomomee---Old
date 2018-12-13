package com.amadoutirera.maakomome.domain


sealed class Either<out L, out R> {


    data class ExecutSuccess<out R>(val b: R) : Either<Nothing, R>()
    data class ExecutError<out L>(val a: L) : Either<L, Nothing>()


    val isExecutSuccess get() = this is ExecutSuccess<R>
    val isExecutError get() = this is ExecutError<L>


    fun <L> left(a: L) = ExecutError(a)
    fun <R> right(b: R) = ExecutSuccess(b)


    fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any =

            when (this) {
                is ExecutError -> fnL(a)
                is ExecutSuccess -> fnR(b)
            }

}




