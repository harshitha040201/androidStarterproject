package com.example.kotlinapp.di

import com.example.kotlinapp.domain.connectivity.ConnectivityChecker
import com.example.kotlinapp.data.connectivity.ConnectivityCheckerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectivityModule {

    @Binds
    @Singleton
    abstract fun bindConnectivityChecker(impl: ConnectivityCheckerImpl): ConnectivityChecker
}
