package com.ahmedkenawy.footballleague.features.list.data

import com.ahmedkenawy.footballleague.core.mapper.Mapper
import com.ahmedkenawy.footballleague.features.list.data.remote.response.CompetitionDto
import com.ahmedkenawy.footballleague.features.list.domain.Competitions
import javax.inject.Inject

/**
 * Mapper class responsible for mapping CompetitionDto objects to Competitions objects.
 */
class CompetitionsMapper @Inject constructor() : Mapper<CompetitionDto, Competitions> {

    /**
     * Method to map a CompetitionDto object to a Competitions object.
     *
     * @param model The CompetitionDto object to be mapped.
     * @return The mapped Competitions object.
     */
    override fun map(model: CompetitionDto): Competitions {
        // Extract data from CompetitionDto and create a Competitions object
        with(model) {
            return Competitions(
                id,           // Competition ID
                area?.name,   // Competition area name
                name,         // Competition name
                emblem        // Competition emblem URL
            )
        }
    }
}
