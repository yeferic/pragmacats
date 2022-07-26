package com.yeferic.pragmacats.di

import com.yeferic.pragmacats.common.Constants
import com.yeferic.pragmacats.data.repository.CatsBreedsRepositoryImp
import com.yeferic.pragmacats.data.source.remote.RemoteService
import com.yeferic.pragmacats.domain.respository.CatsBreedsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CatBreedsModule {

    @Provides
    @Singleton
    fun providesRetrofit(gsonConverterFactory: GsonConverterFactory, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory, okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_SERVICE)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesOKHttpClient() : OkHttpClient{
        return OkHttpClient().newBuilder().addInterceptor { chain ->
            var request = chain.request()
            request.newBuilder().header("x-api-key", Constants.API_KEY).build()
            chain.proceed(request)
        }.build()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRemoteService(retrofit: Retrofit): RemoteService {
        return retrofit.create(RemoteService::class.java)
    }

    @Provides
    @Singleton
    fun providesCatsBreedsRepository(remoteService: RemoteService): CatsBreedsRepository {
        return CatsBreedsRepositoryImp(remoteService)
    }

}