package com.yasincidem.marketim.features.login

import com.yasincidem.marketim.core.MvRxViewModel

class LoginFormViewModel(initialState: LoginFormState) : MvRxViewModel<LoginFormState>(initialState) {
    fun setUsername(prevUsername: String) {
        setState { copy(username = prevUsername) }
    }

    fun setPassword(prevPassword: String) {
        setState { copy(password = prevPassword) }
    }

    fun setWillUserBeRemembered(prevWillUserBeRemembered: Boolean) {
        setState { copy(willUserBeRemembered = prevWillUserBeRemembered) }
    }
}
