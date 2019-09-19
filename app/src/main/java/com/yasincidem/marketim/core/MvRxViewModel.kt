package com.yasincidem.marketim.core

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.BuildConfig
import com.airbnb.mvrx.MvRxState

/**
 * ViewModel'ı initialState ilebirlikte kuruyorum.
 */
abstract class MvRxViewModel<S : MvRxState>(initialState: S) : BaseMvRxViewModel<S>(initialState, debugMode = BuildConfig.DEBUG)
