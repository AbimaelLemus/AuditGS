package com.example.auditgs.data.local

import android.content.Context

class SessionManager(context: Context) {

    private val prefs =
        context.getSharedPreferences(
            "session_prefs",
            Context.MODE_PRIVATE
        )

    companion object {

        private const val KEY_ID_SESSION =
            "key_id_session"

        private const val KEY_TOKEN =
            "key_token"

        private const val KEY_USER =
            "key_user"
    }

    fun saveSession(
        idSession: String,
        token: String,
        user: String
    ) {

        prefs.edit()
            .putString(KEY_ID_SESSION, idSession)
            .putString(KEY_TOKEN, token)
            .putString(KEY_USER, user)
            .apply()
    }

    fun getIdSession(): String? {

        return prefs.getString(
            KEY_ID_SESSION,
            null
        )
    }

    fun getToken(): String? {

        return prefs.getString(
            KEY_TOKEN,
            null
        )
    }

    fun getUser(): String? {

        return prefs.getString(
            KEY_USER,
            null
        )
    }

    fun clearSession() {

        prefs.edit()
            .clear()
            .apply()
    }
}