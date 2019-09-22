package com.yasincidem.marketim.features.login

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Switch
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (loginFormViewModel.isUserAlreadyLoggedIn()) {
            view.findNavController().navigate(R.id.action_login_page_nav_to_main)
        }
    }

    override fun epoxyController(): MvRxEpoxyController = simpleController(loginFormViewModel) {state ->
        loginForm {
            id("loginForm")
            username(state.username)
            password(state.password)
            onBind { _, view, _ ->
                view.setLoginButtonClickListener(clickListener = View.OnClickListener {
                    if (loginFormViewModel.validateForm(
                            state.username,
                            getString(R.string.credential_username),
                            state.password,
                            getString(R.string.credential_password))){
                        loginFormViewModel.setIfUserWillRememberedSharedPref(view.findViewById<Switch>(R.id.remember_me).isChecked)
                        view?.findNavController()?.navigate(R.id.action_login_page_nav_to_main)
                    }
                    else
                        Toast.makeText(context, getString(R.string.wrong_credentials_warning), Toast.LENGTH_LONG).show()
                })
            }
            onUsernameChanged { loginFormViewModel.setUsername(it) }
            onPasswordChanged { loginFormViewModel.setPassword(it) }

        }
    }

    override fun onDestroyView() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
        super.onDestroyView()
    }
}
