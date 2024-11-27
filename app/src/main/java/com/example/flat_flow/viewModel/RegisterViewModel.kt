package com.example.flat_flow.viewModel

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
import android.util.Log

class RegisterViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _senha = MutableStateFlow("")
    val senha: StateFlow<String> = _senha

    private val _repeatPassword = MutableStateFlow("")
    val repeatPassword: StateFlow<String> = _repeatPassword

    private val _enableRegisterButton = MutableStateFlow(false)
    val enableRegisterButton: StateFlow<Boolean> = _enableRegisterButton

    private val _enableMinCharAlert = MutableStateFlow(false)
    val enableMinCharAlert: StateFlow<Boolean> = _enableMinCharAlert

    private val _enablePasswordWontMatchAlert = MutableStateFlow(false)
    val enablePasswordWontMatchAlert: StateFlow<Boolean> = _enablePasswordWontMatchAlert

    private val _toastMessage = MutableStateFlow("")
    val toastMessage: StateFlow<String> = _toastMessage

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        validateForm()
    }

    fun onPasswordChange(newPassword: String) {
        _senha.value = newPassword
        _enableMinCharAlert.value = newPassword.length < 8
        validateForm()
    }

    fun onRepeatPasswordChange(newRepeatPassword: String) {
        _repeatPassword.value = newRepeatPassword
        validateForm()
    }

    private fun validateForm() {
        val isEmailValid = _email.value.isNotEmpty()
        val isPasswordValid = _senha.value.length >= 8
        val doPasswordsMatch = _senha.value == _repeatPassword.value

        _enableRegisterButton.value = isEmailValid && isPasswordValid && doPasswordsMatch
        _enablePasswordWontMatchAlert.value = !doPasswordsMatch
        _enableMinCharAlert.value = !isPasswordValid
    }

    fun register(navController: NavController) {
        viewModelScope.launch {
            try {
                Log.d("Register", "Iniciando requisição de registro...")
                val response = RetrofitInstance.api.register(RegisterRequest(email.value, senha.value))
                if (response.isSuccessful && response.body() != null) {
                    _toastMessage.value = "Register successful"
                    Log.d("Register", "Cadastro realizado com sucesso")
                    navController.navigate("loading/2000/enterRepublic")
                } else {
                    _toastMessage.value = "Register fail: ${response.message()}"
                    Log.e("Register", "Erro no registro: ${response.message()}")
                }
            } catch (e: IOException) {
                _toastMessage.value = "Network error: ${e.message}"
                Log.e("Register", "Erro de rede: ${e.message}", e)
            } catch (e: HttpException) {
                _toastMessage.value = "Server error: ${e.message()}"
                Log.e("Register", "Erro no servidor: ${e.message}", e)
                Log.e("Register", "Código de status: ${e.code()}")
                Log.e("Register", "Corpo do erro: ${e.response()?.errorBody()?.string()}")
            } catch (e: Exception) {
                _toastMessage.value = "Unexpected error: ${e.message}"
                Log.e("Register", "Erro inesperado: ${e.message}", e)
            }
        }
    }

    fun clearToastMessage() {
        _toastMessage.value = ""
    }
}
