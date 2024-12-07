package com.example.flat_flow.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.flat_flow.AppSession
import com.example.flat_flow.model.data.RepublicCreateRequest
import com.example.flat_flow.model.data.RepublicEnterRequest
import com.example.flat_flow.model.data.api.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RepublicEnterViewModel : ViewModel() {
    var idRepublicaInserido: MutableState<String> = mutableStateOf("")
    var createMessage: MutableState<String> = mutableStateOf("")
    var enterMessage: MutableState<String> = mutableStateOf("")

    // Função de entrar que chama a API
    fun republicEnter(navController: NavController) {
        viewModelScope.launch {
            val response = try {
                // Chama o endpoint de login
                RetrofitInstance.api.republicEnter(RepublicEnterRequest(idRepublicaInserido.value, AppSession.userSession.idUsuario) )
            } catch (e: IOException) {
                enterMessage.value = "Network error: ${e.message}"
                return@launch
            } catch (e: HttpException) {
                enterMessage.value = "Server error: ${e.message}"
                return@launch
            }
            if (response.isSuccessful) {
                val body = response.body()
                enterMessage.value = "Successful!"
                if (body != null) {
                    AppSession.userSession.idRepublica = body.PisoCompartido_idPisoCompartido
                    AppSession.userSession.idRepublicaInserido = idRepublicaInserido.value
                }
                if (idRepublicaInserido.value.toInt() != 1) {
                    navController.navigate("loading/2000/home")
                } else {
                    enterMessage.value = "Enter republic failed: ${response.message()}"
                }
            }
        }
    }
    // Função de create que chama a API
    fun createRepublic(navController: NavController) {
        viewModelScope.launch {
            val response = try {
                // Chama o endpoint de login
                RetrofitInstance.api.republicCreate(RepublicCreateRequest(idRepublicaInserido.value, AppSession.userSession.idUsuario))
            } catch (e: IOException) {
                createMessage.value = "Network error: ${e.message}"
                return@launch
            } catch (e: HttpException) {
                createMessage.value = "Server error: ${e.message}"
                return@launch
            }
            if (response.isSuccessful) {
                val body = response.body()
                createMessage.value = "Successful login!"
                if (body != null) {
                    AppSession.userSession.idRepublica = body.PisoCompartido_idPisoCompartido
                    navController.navigate("loading/2000/home")
                }
                if (AppSession.userSession.idRepublica == 1) {
                    navController.navigate("loading/2000/enterRepublic")
                }
            } else {
                createMessage.value = "Login failed: ${response.message()}"
            }
        }
    }
}