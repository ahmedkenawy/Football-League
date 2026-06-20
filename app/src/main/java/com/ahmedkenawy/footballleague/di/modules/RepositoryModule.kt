package com.ahmedkenawy.footballleague.di.modules

import com.ahmedkenawy.footballleague.features.list.data.CompetitionRepository
import com.ahmedkenawy.footballleague.features.list.data.ICompetitionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompetitionRepository(impl: CompetitionRepository): ICompetitionRepository
}
