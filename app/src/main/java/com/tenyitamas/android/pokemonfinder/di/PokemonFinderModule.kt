package com.tenyitamas.android.pokemonfinder.di

import com.tenyitamas.android.pokemonfinder.data.PokemonApi
import com.tenyitamas.android.pokemonfinder.data.repository.PokemonRepositoryImpl
import com.tenyitamas.android.pokemonfinder.domain.repository.PokemonRepository
import com.tenyitamas.android.pokemonfinder.domain.use_case.GetAllPokemonUseCase
import com.tenyitamas.android.pokemonfinder.domain.use_case.GetPokemonInfoUseCase
import com.tenyitamas.android.pokemonfinder.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonFinderModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonApi(client: OkHttpClient): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi): PokemonRepository {
        return PokemonRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideGetAllPokemonUseCase(repository: PokemonRepository): GetAllPokemonUseCase {
        return GetAllPokemonUseCase(repository = repository)
    }

    @Provides
    @Singleton
    fun provideGetPokemonInfoUseCase(repository: PokemonRepository): GetPokemonInfoUseCase {
        return GetPokemonInfoUseCase(repository = repository)
    }
}