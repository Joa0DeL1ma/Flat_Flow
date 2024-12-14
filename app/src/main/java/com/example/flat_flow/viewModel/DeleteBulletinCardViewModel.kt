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

class DeleteBulletinCardViewModel : ViewModel() {
    private val _clickableBulletinCard = mutableStateOf(false)
    val clickableBulletinCard: State<Boolean> = _clickableBulletinCard
    var deleteBulletinCardMessage: MutableState<String> = mutableStateOf("")
    var idMuro: MutableState<Int> = mutableStateOf(-1) // ID do cartão a ser deletado

    fun toggleClickableBulletinCard() {
        _clickableBulletinCard.value = !_clickableBulletinCard.value
    }

    fun deleteBulletinCard(navController: NavController) {
        viewModelScope.launch {
            // Validação para evitar chamadas desnecessárias
            if (idMuro.value == -1) {
                deleteBulletinCardMessage.value = "Invalid card ID."
                return@launch
            }

            val response = try {
                // Chama o endpoint de deleteBulletinCard com os parâmetros de consulta
                RetrofitInstance.api.deleteBulletinCard(
                    idMuro = idMuro.value,
                    PisoCompartido_idPisoCompartido = AppSession.userSession.idRepublica
                )
            } catch (e: IOException) {
                deleteBulletinCardMessage.value = "Network error: ${e.message}"
                return@launch
            } catch (e: HttpException) {
                deleteBulletinCardMessage.value = "Server error: ${e.message}"
                return@launch
            }

            if (response.isSuccessful) {
                delay(500)
                navController.navigate("home")
                deleteBulletinCardMessage.value = "Successful deletion!"
            } else {
                deleteBulletinCardMessage.value = "Failed to delete card."
            }
        }
    }
}
