package com.example.flat_flow.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.flat_flow.model.data.RegisterRequest
import com.example.flat_flow.model.data.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

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
        val isEmailValid = _email.value.isNotEmpty()
        val isPasswordValid = _password.value.length >= 8
        val doPasswordsMatch = _password.value == _repeatPassword.value

        _enableRegisterButton.value = isEmailValid && isPasswordValid && doPasswordsMatch
        _enablePasswordWontMatchAlert.value = !doPasswordsMatch
        _enableMinCharAlert.value = !isPasswordValid
    }

    // Função de registro que chama a API
    fun register(navController: NavController): String {
        var resultMessage = "Unknown error" // Mensagem padrão

        viewModelScope.launch {
            val response = try {
                // Chama o endpoint de registro
                RetrofitInstance.api.register(RegisterRequest(email.value, password.value))
            } catch (e: IOException) {
                resultMessage = "Network error: ${e.message}"
                return@launch
            } catch (e: HttpException) {
                resultMessage = "Server error: ${e.message}"
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                resultMessage = "Register successful"
                navController.navigate("loading/2000/enterRepublic")
            } else {
                resultMessage = "Register fail: ${response.message()}"
            }
        }
        return resultMessage
    }
}

