package com.amadoutirera.maakomome.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
        @PrimaryKey
        var id: String ="",
        var id_mk: String="",
        var firstName: String="",
        var lastName : String="",
        var tel_number : String="",
        var profilPic : String=""

):Comparable<User> {

    override fun compareTo(other: User) = 0
}
