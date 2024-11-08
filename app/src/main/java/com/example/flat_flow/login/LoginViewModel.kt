package com.example.flat_flow.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.flat_flow.model.data.LoginRequest
import com.example.flat_flow.model.data.api.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class LoginViewModel : ViewModel() {
    var email: MutableState<String> = mutableStateOf("")
    var password: MutableState<String> = mutableStateOf("")
    var enableWrongLoginAlert: MutableState<Boolean> = mutableStateOf(false)
    var enableLoginButton: MutableState<Boolean> = mutableStateOf(false)


    // Atualiza o estado do botão de login com base nos campos
    fun updateLoginButtonState() {
        enableLoginButton.value = email.value.isNotBlank() && password.value.isNotBlank()
    }

    // Função de login que chama a API
    fun login(navController: NavController): String {
        var resultMessage = "Unknown error" // Mensagem padrão

        viewModelScope.launch {
            val response = try {
                // Chama o endpoint de login
                RetrofitInstance.api.login(LoginRequest(email.value, password.value))
            } catch (e: IOException) {
                resultMessage = "Network error: ${e.message}"
                return@launch
            } catch (e: HttpException) {
                resultMessage = "Server error: ${e.message}"
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                resultMessage = "Successful login!"
                navController.navigate("loading/2000/enterRepublic")
            } else {
                resultMessage = "Login failed: ${response.message()}"
            }
        }
        return resultMessage
    }
}