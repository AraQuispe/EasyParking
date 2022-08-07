package com.ges.easyparking.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFireStoreInstance() = FirebaseFirestore.getInstance()

    /*@Provides
    @Singleton
    fun provideUserList(
        firestore: FirebaseFirestore
    ) = firestore.collection("users")
*/
}