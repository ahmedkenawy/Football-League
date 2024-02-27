package com.ahmedkenawy.footballleague.features.list.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "competitions")
@Parcelize
data class Competitions(
    @PrimaryKey
    val id: Int,
    val areaName: String?,
    val competitionName: String?,
    val competitionEmblem: String?
) : Parcelable