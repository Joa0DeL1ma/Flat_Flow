package com.example.flat_flow.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.flat_flow.model.mockUsers

class LoginViewModel : ViewModel() {
    var email: MutableState<String> = mutableStateOf("")
    var password: MutableState<String> = mutableStateOf("")
    var enableWrongLoginAlert: MutableState<Boolean> = mutableStateOf(false)
    var enableLoginButton: MutableState<Boolean> = mutableStateOf(false)

    // Função para verificar o login
    fun verifyLogin(): Boolean =
        mockUsers.any { mockUser ->
            mockUser.email == email.value && mockUser.password == password.value
        }

    // Atualiza o estado do botão de login com base nos campos
    fun updateLoginButtonState() {
        enableLoginButton.value = email.value.isNotBlank() && password.value.isNotBlank()
    }
}
