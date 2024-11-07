package com.example.flat_flow.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _repeatPassword = MutableStateFlow("")
    val repeatPassword: StateFlow<String> = _repeatPassword

    private val _enableRegisterButton = MutableStateFlow(false)
    val enableRegisterButton: StateFlow<Boolean> = _enableRegisterButton

    private val _enableMinCharAlert = MutableStateFlow(false)
    val enableMinCharAlert: StateFlow<Boolean> = _enableMinCharAlert

    private val _enablePasswordWontMatchAlert = MutableStateFlow(false)
    val enablePasswordWontMatchAlert: StateFlow<Boolean> = _enablePasswordWontMatchAlert

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        validateForm()
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        _enableMinCharAlert.value = newPassword.length < 8
        validateForm()
    }

    fun onRepeatPasswordChange(newRepeatPassword: String) {
        _repeatPassword.value = newRepeatPassword
        validateForm()
    }

    private fun validateForm() {
        _enableRegisterButton.value = _email.value.isNotEmpty() &&
            _password.value.isNotEmpty() &&
            _password.value == _repeatPassword.value &&
            _password.value.length >= 8
        _enablePasswordWontMatchAlert.value = _password.value != _repeatPassword.value
    }
}
