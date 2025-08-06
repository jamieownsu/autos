package com.chalupin.carfax.core.di

import android.content.Context
import com.chalupin.carfax.data.api.ListingService
import com.chalupin.carfax.data.db.ListingDatabase
import com.chalupin.carfax.data.db.dao.ListingDao
import com.chalupin.carfax.data.repository.ListingRepositoryImpl
import com.chalupin.carfax.domain.repository.ListingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://carfax-for-consumers.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideListingService(retrofit: Retrofit): ListingService {
        return retrofit.create(ListingService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ListingDatabase {
        return ListingDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideListingDao(database: ListingDatabase): ListingDao {
        return database.listingDao()
    }

    @Provides
    @Singleton
    fun provideListingRepository(listingRepositoryImpl: ListingRepositoryImpl): ListingRepository {
        return listingRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}