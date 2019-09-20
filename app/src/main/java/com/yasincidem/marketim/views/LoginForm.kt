package com.yasincidem.marketim.views

import android.content.Context
import android.text.Editable
import android.text.Spanned
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.*
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.yasincidem.marketim.R


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT)
class LoginForm @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr) {

    private val usernameET by lazy { findViewById<EditText>(R.id.username) }
    private val passwordET by lazy { findViewById<EditText>(R.id.password) }
    private val rememberMeSwitch by lazy { findViewById<Switch>(R.id.remember_me) }
    private val loginButton by lazy { findViewById<Button>(R.id.login_button) }

    private val usernameWatcher: TextWatcher = SimpleTextWatcher { onUsernameChanged?.invoke(it) }
    private val passwordWatcher: TextWatcher = SimpleTextWatcher { onPasswordChanged?.invoke(it) }

    init {
        inflate(context, R.layout.fragment_login_form, this)
        usernameET.addTextChangedListener(usernameWatcher)
        passwordET.addTextChangedListener(passwordWatcher)
    }

    @TextProp
    fun setUsername(username: CharSequence?) {
        usernameET.setTextIfDifferent(username)
    }

    @TextProp
    fun setPassword(password: CharSequence?) {
        passwordET.setTextIfDifferent(password)
    }

    @set:CallbackProp
    var onUsernameChanged: ((newText: String) -> Unit)? = null

    @set:CallbackProp
    var onPasswordChanged: ((newText: String) -> Unit)? = null

    @CallbackProp
    fun setLoginButtonClickListener(clickListener: OnClickListener?) {
        loginButton.setOnClickListener(clickListener)
    }

    @CallbackProp
    fun setRememberMeChangeListener(checkedChangeListener: CompoundButton.OnCheckedChangeListener?) {
        rememberMeSwitch.setOnCheckedChangeListener(checkedChangeListener)
    }

}

fun EditText.setTextIfDifferent(newText: CharSequence?): Boolean {
    if (!isTextDifferent(newText, text)) {
        return false
    }

    setText(newText)
    setSelection(newText?.length ?: 0)
    return true
}

fun isTextDifferent(str1: CharSequence?, str2: CharSequence?): Boolean {
    if (str1 === str2) {
        return false
    }
    if (str1 == null || str2 == null) {
        return true
    }
    val length = str1.length
    if (length != str2.length) {
        return true
    }

    if (str1 is Spanned) {
        return str1 != str2
    }

    for (i in 0 until length) {
        if (str1[i] != str2[i]) {
            return true
        }
    }
    return false
}

private class SimpleTextWatcher(val onTextChanged: (newText: String) -> Unit) : TextWatcher {
    override fun afterTextChanged(s: Editable) {
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        onTextChanged(s.toString())
    }
}