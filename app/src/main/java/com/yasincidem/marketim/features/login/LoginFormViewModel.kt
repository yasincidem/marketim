package com.yasincidem.marketim.features.login

import android.content.SharedPreferences
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.yasincidem.marketim.core.MvRxViewModel
import org.koin.android.ext.android.inject

class LoginFormViewModel(
    initialState: LoginFormState,
    private val sharedPreferences: SharedPreferences,
    private val sharedPreferencesEditor: SharedPreferences.Editor
) : MvRxViewModel<LoginFormState>(initialState) {
    fun setUsername(username: String) {
        setState { copy(username = username) }
    }

    fun setPassword(password: String) {
        setState { copy(password = password) }
    }

    fun isUserAlreadyLoggedIn() = sharedPreferences.getBoolean("remember_me", false)

    fun setIfUserWillRemembered(value: Boolean) {
        sharedPreferencesEditor.putBoolean("remember_me", value)
        sharedPreferencesEditor.apply()
    }

    fun validateForm(username: String, expectedUsername: String, password: String, expectedPassword: String) =
         (username.trim() == expectedUsername) and (password == expectedPassword)

    companion object : MvRxViewModelFactory<LoginFormViewModel, LoginFormState> {
        override fun create(viewModelContext: ViewModelContext, state: LoginFormState): LoginFormViewModel {
            val sharedPreferences: SharedPreferences by viewModelContext.activity.inject()
            val sharedPreferencesEditor: SharedPreferences.Editor by viewModelContext.activity.inject()
            return LoginFormViewModel(state, sharedPreferences, sharedPreferencesEditor)
        }
    }
}

