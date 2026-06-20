package com.ahmedkenawy.footballleague.features.list.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ahmedkenawy.footballleague.features.list.domain.Competitions

@Entity(tableName = "competitions")
data class CompetitionEntity(
    @PrimaryKey val id: Int,
    val areaName: String?,
    val competitionName: String?,
    val competitionEmblem: String?
)

fun CompetitionEntity.toDomain() = Competitions(id, areaName, competitionName, competitionEmblem)

fun Competitions.toEntity() = CompetitionEntity(id, areaName, competitionName, competitionEmblem)
