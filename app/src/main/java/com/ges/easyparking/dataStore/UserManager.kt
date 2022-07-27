package com.ges.easyparking.dataStore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(context: Context) {

    private val dataStore: DataStore<Preferences> = context.createDataStore(name = "user_states")

    companion object {
        val LOGIN_STATE_KEY :Preferences.Key<Boolean> = preferencesKey<Boolean>("LOGIN_STATE")
        val DISPLAY_TOPBAR_KEY :Preferences.Key<Boolean> = preferencesKey<Boolean>("DISPLAY_TOPBAR")
    }

    suspend fun storeLoginState(state: Boolean){
        dataStore.edit {
            it[LOGIN_STATE_KEY] = state
        }
    }

    suspend fun storeDisplayTopBar(state: Boolean) {
        dataStore.edit {
            it[DISPLAY_TOPBAR_KEY] = state
        }
    }

    val loginStateFlow: Flow<Boolean> = dataStore.data.map {
        it[LOGIN_STATE_KEY] ?: false
    }

    val displayTopFlow: Flow<Boolean> = dataStore.data.map {
        it[DISPLAY_TOPBAR_KEY] ?: true
    }

}