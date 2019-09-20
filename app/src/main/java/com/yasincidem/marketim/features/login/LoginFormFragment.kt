package com.yasincidem.marketim.features.login

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import com.airbnb.mvrx.fragmentViewModel
import com.yasincidem.marketim.R
import com.yasincidem.marketim.core.BaseEpoxyFragment
import com.yasincidem.marketim.core.MvRxEpoxyController
import com.yasincidem.marketim.core.simpleController
import com.yasincidem.marketim.views.loginForm

class LoginFormFragment: BaseEpoxyFragment() {
    private val loginFormViewModel: LoginFormViewModel by fragmentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = getString(R.string.login_form_fragment_title)
    }

    override fun epoxyController(): MvRxEpoxyController = simpleController(loginFormViewModel) {state ->
        loginForm {
            id("loginForm")
            username(state.username)
            password(state.password)
            onUsernameChanged { loginFormViewModel.setUsername(it) }
            onPasswordChanged { loginFormViewModel.setPassword(it) }
            loginButtonClickListener { _ ->
//                if (state.username == getString(R.string.credential_username) && state.password == getString(R.string.credential_password))
                    view?.findNavController()?.navigate(R.id.action_login_page_nav_to_main)
//                else
//                    Toast.makeText(context, getString(R.string.wrong_credentials_warning), Toast.LENGTH_LONG).show()
            }
        }
    }
}
