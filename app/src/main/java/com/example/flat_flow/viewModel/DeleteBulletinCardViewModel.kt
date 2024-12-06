package com.example.flat_flow.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.flat_flow.AppSession
import com.example.flat_flow.model.data.DeleteBulletinCardRequest
import com.example.flat_flow.model.data.api.RetrofitInstance
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DeleteBulletinCardViewModel: ViewModel() {
    private val _clickableBulletinCard = mutableStateOf(false)
    val clickableBulletinCard: State<Boolean> = _clickableBulletinCard
    var deleteBulletinCardMessage: MutableState<String> = mutableStateOf("")
    var informaciones: MutableState<String> = mutableStateOf("")

    fun toggleClickableBulletinCard() {
        _clickableBulletinCard.value = !_clickableBulletinCard.value
    }
    fun deleteBulletinCard(navController: NavController) {
        viewModelScope.launch {
            val response = try {
                // Chama o endpoint de deleteBulletinCard
                RetrofitInstance.api.deleteBulletinCard(
                    DeleteBulletinCardRequest(
                        idRepublica = AppSession.userSession.idRepublica,
                        informaciones = informaciones.value
                    )
                )
            } catch (e: IOException) {
                deleteBulletinCardMessage.value = "Network error: ${e.message}"
                return@launch
            } catch (e: HttpException) {
                deleteBulletinCardMessage.value = "Server error: ${e.message}"
                return@launch
            }
            if (response.isSuccessful) {
                delay(2000)
                navController.navigate("home")
                deleteBulletinCardMessage.value = "Successful deletion!"
            }
        }
    }
}

