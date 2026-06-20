package com.ahmedkenawy.footballleague.features.list.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Competitions(
    val id: Int,
    val areaName: String?,
    val competitionName: String?,
    val competitionEmblem: String?
) : Parcelable
