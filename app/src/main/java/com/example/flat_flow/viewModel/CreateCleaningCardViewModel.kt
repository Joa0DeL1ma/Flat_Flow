package com.example.flat_flow.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.flat_flow.AppSession
import com.example.flat_flow.model.data.CreateCleaningCardRequest
import com.example.flat_flow.model.data.api.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CreateCleaningCardViewModel : ViewModel() {

    var quehacer: MutableState<String> = mutableStateOf("")
    var diaVencimiento: MutableState<String> = mutableStateOf("")
    var createCleaningCardMessage: MutableState<String> = mutableStateOf("")

    // Função de createCleaningCard que chama a API
    fun createCleaningCard(navController: NavController) {
        viewModelScope.launch {
            val response = try {
                // Chama o endpoint de createCleaningCard
                RetrofitInstance.api.createCleaningCard(
                    CreateCleaningCardRequest(
                        quehacer = quehacer.value,
                        diaVencimiento = diaVencimiento.value,
                        Usuario_idUsuarios = AppSession.userSession.idUsuario,
                        PisoCompartido_idPisoCompartido = AppSession.userSession.idRepublica
                    )
                )
            } catch (e: IOException) {
                createCleaningCardMessage.value = "Network error: ${e.message}"
                return@launch
            } catch (e: HttpException) {
                createCleaningCardMessage.value = "Server error: ${e.message}"
                return@launch
            }
            if (response.isSuccessful) {
                createCleaningCardMessage.value = "Successful creation!"
                navController.navigate("home")
            }
        }
    }
}