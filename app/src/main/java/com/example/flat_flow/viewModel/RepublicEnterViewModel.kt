package com.example.flat_flow.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.flat_flow.AppSession
import com.example.flat_flow.model.data.RepublicEnterRequest
import com.example.flat_flow.model.data.api.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RepublicEnterViewModel: ViewModel() {
    var republica: MutableState<String> = mutableStateOf("")
    var enterMessage: MutableState<String> = mutableStateOf("")

    // Função de entrar que chama a API
    fun republicEnter(navController: NavController) {
        viewModelScope.launch {
            val response = try {
                // Chama o endpoint de login
                RetrofitInstance.api.republicEnter(RepublicEnterRequest(republica.value))
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
                    AppSession.userSession.republica = republica.value
                }
                navController.navigate("loading/2000/home")
            } else {
                enterMessage.value = "Enter republic failed: ${response.message()}"
            }
        }
    }
}