package com.example.flat_flow.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.flat_flow.AppSession
import com.example.flat_flow.model.data.CreateBillCardRequest
import com.example.flat_flow.model.data.api.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CreateBillCardViewModel : ViewModel() {

    var valor: MutableState<String> = mutableStateOf("")
    var compra: MutableState<String> = mutableStateOf("")
    var diaVencimiento: MutableState<String> = mutableStateOf("")
    var createBillCardMessage: MutableState<String> = mutableStateOf("")

    // Função de CreateBillCard que chama a API
    fun createBillCard(navController: NavController) {
        viewModelScope.launch {
            val response = try {
                // Chama o endpoint de CreateBillCard
                RetrofitInstance.api.createBillCard(
                    CreateBillCardRequest(
                        valor = valor.value,
                        diaVencimiento = diaVencimiento.value,
                        compra = compra.value,
                        PisoCompartido_idPisoCompartido = AppSession.userSession.idRepublica,
                        Usuario_idUsuarios = AppSession.userSession.idUsuario
                    )
                )
            } catch (e: IOException) {
                createBillCardMessage.value = "Network error: ${e.message}"
                return@launch
            } catch (e: HttpException) {
                createBillCardMessage.value = "Server error: ${e.message}"
                return@launch
            }
            if (response.isSuccessful) {
                createBillCardMessage.value = "Successful creation!"
                navController.navigate("home")
            }
        }
    }
}