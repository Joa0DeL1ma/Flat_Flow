package com.example.flat_flow.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.flat_flow.AppSession
import com.example.flat_flow.model.data.api.RetrofitInstance
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DeleteCleaningCardViewModel() : ViewModel() {
    private val _clickableCleaningCard = mutableStateOf(false)
    val clickableCleaningCard: State<Boolean> = _clickableCleaningCard
    var deleteCleaningCardMessage: MutableState<String> = mutableStateOf("")
    var idCalendario: MutableState<Int> = mutableStateOf(-1)

    fun toggleClickableCleaningCard() {
        _clickableCleaningCard.value = !_clickableCleaningCard.value
    }

    fun deleteCleaningCard(navController: NavController) {
        viewModelScope.launch {
            if (idCalendario.value == -1) {
                deleteCleaningCardMessage.value = "Invalid card ID."
                return@launch
            }
            val response = try {
                // Chama o endpoint de deleteBillCard
                RetrofitInstance.api.deleteCleaningCard(
                        PisoCompartido_idPisoCompartido = AppSession.userSession.idRepublica,
                        idCalendario = idCalendario.value
                )
            } catch (e: IOException) {
                deleteCleaningCardMessage.value = "Network error: ${e.message}"
                return@launch
            } catch (e: HttpException) {
                deleteCleaningCardMessage.value = "Server error: ${e.message}"
                return@launch
            }
            if (response.isSuccessful) {
                delay(500)
                navController.navigate("home")
                deleteCleaningCardMessage.value = "Successful deletion!"
            }
        }
    }
}

