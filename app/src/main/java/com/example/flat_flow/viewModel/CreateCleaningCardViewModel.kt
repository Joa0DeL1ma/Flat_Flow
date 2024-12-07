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

    // A data é armazenada como String no formato "yyyy/MM/dd"
    var diaVencimiento: MutableState<String> = mutableStateOf("")

    var createCleaningCardMessage: MutableState<String> = mutableStateOf("")

    // Função para criar o cleaning card
    fun createCleaningCard(navController: NavController) {
        viewModelScope.launch {
            val response = try {
                // Aqui, convertendo a String para Date diretamente
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = sdf.parse(diaVencimiento.value)

                // Chama o endpoint de createCleaningCard
                RetrofitInstance.api.createCleaningCard(
                    CreateCleaningCardRequest(
                        quehacer = quehacer.value,
                        diaVencimiento = formattedDate, // Envia a data formatada como Date
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
            } catch (e: Exception) {
                createCleaningCardMessage.value = "Error parsing date: ${e.message}"
                return@launch
            }

            if (response.isSuccessful) {
                createCleaningCardMessage.value = "Successful creation!"
                navController.navigate("home")
            }
        }
    }
}
