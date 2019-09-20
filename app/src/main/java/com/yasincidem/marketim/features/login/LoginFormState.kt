package com.yasincidem.marketim.features.login

import com.airbnb.mvrx.MvRxState

data class LoginFormState(
    val username: String = "",
    val password: String = "",
    val willUserBeRemembered: Boolean = false
) : MvRxState
