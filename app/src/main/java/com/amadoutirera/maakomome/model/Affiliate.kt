package com.amadoutirera.maakomome.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/*
@Entity(foreignKeys = [

ForeignKey(entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"])

])
*/

@Entity
data class Affiliate(
        @PrimaryKey var id: String ="",
        var userId : String ="",
        var fullName: String="",
        var tel_number : String=""


) :Comparable<Affiliate> {

        override fun compareTo(other: Affiliate) = 0
}
