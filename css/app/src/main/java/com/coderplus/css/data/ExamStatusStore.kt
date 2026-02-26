package com.coderplus.css.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Almacena estado local del examen.
 *
 * Por ahora el estado es "Enviado" cuando el estudiante marca que ya envió el formulario.
 * Más adelante, cuando se integre Microsoft Forms/Excel/correo, este estado se podrá validar.
 */
private val Context.dataStore by preferencesDataStore(name = "exam_status")

class ExamStatusStore(private val context: Context) {

    companion object {
        private val KEY_SENT = booleanPreferencesKey("exam_sent")
        private val KEY_SENT_AT = longPreferencesKey("exam_sent_at")
    }

    val examSent: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_SENT] ?: false
    }

    val sentAt: Flow<Long> = context.dataStore.data.map { prefs ->
        prefs[KEY_SENT_AT] ?: 0L
    }

    suspend fun markSent() {
        context.dataStore.edit { prefs ->
            prefs[KEY_SENT] = true
            prefs[KEY_SENT_AT] = System.currentTimeMillis()
        }
    }

    suspend fun reset() {
        context.dataStore.edit { prefs ->
            prefs[KEY_SENT] = false
            prefs[KEY_SENT_AT] = 0L
        }
    }
}
