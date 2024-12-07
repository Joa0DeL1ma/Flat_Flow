package com.example.flat_flow.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.flat_flow.AppSession
import com.example.flat_flow.model.data.LoginRequest
import com.example.flat_flow.model.data.api.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class LoginViewModel : ViewModel() {
    var email: MutableState<String> = mutableStateOf("")
    var contraseña: MutableState<String> = mutableStateOf("")
    var enableWrongLoginAlert: MutableState<Boolean> = mutableStateOf(false)
    var enableLoginButton: MutableState<Boolean> = mutableStateOf(false)
    var loginMessage: MutableState<String> = mutableStateOf("")

    // Atualiza o estado do botão de login com base nos campos
    fun updateLoginButtonState() {
        enableLoginButton.value = email.value.isNotBlank() && contraseña.value.isNotBlank()
    }

    // Função de login que chama a API
    fun login(navController: NavController) {
        viewModelScope.launch {
            val response = try {
                // Chama o endpoint de login
                RetrofitInstance.api.login(LoginRequest(email.value, contraseña.value))
            } catch (e: IOException) {
                loginMessage.value = "Network error: ${e.message}"
                return@launch
            } catch (e: HttpException) {
                loginMessage.value = "Server error: ${e.message}"
                return@launch
            }
            if (response.isSuccessful) {
                val body = response.body()
                loginMessage.value = "Successful login!"
                if (body != null) {
                        AppSession.userSession.idRepublica = body.PisoCompartido_idPisoCompartido
                        AppSession.userSession.idUsuario = body.idUsuarios
                        AppSession.userSession.idRepublicaInserido = body.codigo
                }
                if (AppSession.userSession.idRepublica == 1) {
                    navController.navigate("loading/2000/enterRepublic")
                }
                if (AppSession.userSession.idRepublica != 1) {
                    navController.navigate("home")
                }
            } else {
                loginMessage.value = "Login failed: ${response.message()}"
            }
        }
    }
}