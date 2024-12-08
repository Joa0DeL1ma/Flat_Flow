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
import java.text.SimpleDateFormat
import java.util.Locale

class CreateBillCardViewModel : ViewModel() {

    var valor: MutableState<String> = mutableStateOf("")
    var compra: MutableState<String> = mutableStateOf("")
    // A data será sempre um valor válido no formato "yyyy/MM/dd"
    var diaVencimiento: MutableState<String> = mutableStateOf("")
    var createBillCardMessage: MutableState<String> = mutableStateOf("")

    fun createBillCard(navController: NavController) {
        viewModelScope.launch {
            try {
                // Validar o formato da data como "yyyy-MM-dd"
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate: String = try {
                    val date = sdf.parse(diaVencimiento.value)
                    sdf.format(date!!)
                } catch (e: Exception) {
                    createBillCardMessage.value = "Invalid date format. Please use yyyy-MM-dd."
                    return@launch
                }
                // Chama o endpoint de CreateBillCard
                val response = RetrofitInstance.api.createBillCard(
                    CreateBillCardRequest(
                        valor = valor.value,
                        diaVencimiento = formattedDate, // Envia a data no formato esperado pelo banco
                        compra = compra.value,
                        PisoCompartido_idPisoCompartido = AppSession.userSession.idRepublica
                    )
                )

                if (response.isSuccessful) {
                    createBillCardMessage.value = "Successful creation!"
                    navController.navigate("home")
                } else {
                    createBillCardMessage.value = "Failed to create bill card."
                }

            } catch (e: IOException) {
                createBillCardMessage.value = "Network error: ${e.message}"
            } catch (e: HttpException) {
                createBillCardMessage.value = "Server error: ${e.message}"
            } catch (e: Exception) {
                createBillCardMessage.value = "Unknown error: ${e.message}"
            }
        }
    }
}
