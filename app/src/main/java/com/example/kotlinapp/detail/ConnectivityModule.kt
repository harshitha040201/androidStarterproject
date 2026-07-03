package com.example.kotlinapp.detail

import com.example.kotlinapp.detail.ConnectivityChecker
import com.example.kotlinapp.detail.ConnectivityCheckerImpl
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