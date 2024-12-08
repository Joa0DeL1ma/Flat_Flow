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
import java.text.SimpleDateFormat
import java.util.*

class CreateCleaningCardViewModel : ViewModel() {

    var quehacer: MutableState<String> = mutableStateOf("")
    var diaVencimiento: MutableState<String> = mutableStateOf("") // Data como String
    var createCleaningCardMessage: MutableState<String> = mutableStateOf("")

    // Função para criar o cleaning card
    fun createCleaningCard(navController: NavController) {
        // Validação simples para evitar chamadas desnecessárias
        if (quehacer.value.isEmpty() || diaVencimiento.value.isEmpty()) {
            createCleaningCardMessage.value = "Please fill all fields."
            return
        }

        viewModelScope.launch {
            try {
                // Validar o formato da data como "yyyy-MM-dd"
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate: String = try {
                    val date = sdf.parse(diaVencimiento.value)
                    sdf.format(date!!)
                } catch (e: Exception) {
                    createCleaningCardMessage.value = "Invalid date format. Please use yyyy-MM-dd."
                    return@launch
                }

                val response = RetrofitInstance.api.createCleaningCard(
                    CreateCleaningCardRequest(
                        quehacer = quehacer.value,
                        diaVencimiento = formattedDate, // Envia a data no formato esperado pelo banco
                        Usuario_idUsuarios = AppSession.userSession.idUsuario,
                        PisoCompartido_idPisoCompartido = AppSession.userSession.idRepublica
                    )
                )

                if (response.isSuccessful) {
                    createCleaningCardMessage.value = "Card created successfully!"
                    navController.navigate("home")
                } else {
                    createCleaningCardMessage.value = "Failed to create card: ${response.errorBody()?.string()}"
                }
            } catch (e: IOException) {
                createCleaningCardMessage.value = "Network error: ${e.message}"
            } catch (e: HttpException) {
                createCleaningCardMessage.value = "Server error: ${e.message}"
            } catch (e: Exception) {
                createCleaningCardMessage.value = "Unexpected error: ${e.message}"
            }
        }
    }
}
