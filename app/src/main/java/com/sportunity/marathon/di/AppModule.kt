package com.sportunity.marathon.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sportunity.marathon.data.local.database.MarathonDatabase
import com.sportunity.marathon.data.remote.service.MarathonService.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideArtObjectDB(@ApplicationContext context: Context): MarathonDatabase {
        return Room.databaseBuilder(
            context,
            MarathonDatabase::class.java,
            MarathonDatabase.DATABASE_NAME,
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        return builder.build()
    }

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Singleton
    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        val contentType = "application/json".toMediaType()
        val jsonConverter = json.asConverterFactory(contentType)
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(jsonConverter)
            .baseUrl(BASE_URL)
    }
}