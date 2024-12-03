package com.example.flat_flow.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.flat_flow.AppSession
import com.example.flat_flow.model.data.CreateBulletinCardRequest
import com.example.flat_flow.model.data.api.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CreateBulletinCardViewModel : ViewModel() {

    var title: MutableState<String> = mutableStateOf("")
    var content: MutableState<String> = mutableStateOf("")
    var createBulletinCardMessage: MutableState<String> = mutableStateOf("")

    // Função de createCleaningCard que chama a API
    fun createBulletinCard(navController: NavController) {
        viewModelScope.launch {
            val response = try {
                // Chama o endpoint de createBulletinCard
                RetrofitInstance.api.createBulletinCard(
                    CreateBulletinCardRequest(
                        title = title.value,
                        content = content.value,
                        republicId = AppSession.userSession.republica
                    )
                )
            } catch (e: IOException) {
                createBulletinCardMessage.value = "Network error: ${e.message}"
                return@launch
            } catch (e: HttpException) {
                createBulletinCardMessage.value = "Server error: ${e.message}"
                return@launch
            }
            if (response.isSuccessful) {
                createBulletinCardMessage.value = "Successful creation!"
                navController.navigate("home")
            }
        }
    }
}