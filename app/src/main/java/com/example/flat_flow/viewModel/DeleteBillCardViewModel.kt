package com.example.flat_flow.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.flat_flow.AppSession
import com.example.flat_flow.model.data.DeleteBillCardRequest
import com.example.flat_flow.model.data.DeleteBulletinCardRequest
import com.example.flat_flow.model.data.api.RetrofitInstance
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DeleteBillCardViewModel: ViewModel() {
    private val _clickableBillCard = mutableStateOf(false)
    val clickableBillCard: State<Boolean> = _clickableBillCard
    var deleteBillCardMessage: MutableState<String> = mutableStateOf("")
    var compra: MutableState<String> = mutableStateOf("")


    fun toggleClickableBillCard() {
        _clickableBillCard.value = !_clickableBillCard.value
    }

    fun deleteBillCard(navController: NavController) {
        viewModelScope.launch {
            val response = try {
                // Chama o endpoint de deleteBillCard
                RetrofitInstance.api.deleteBillCard(
                    DeleteBillCardRequest(
                        PisoCompartido_idPisoCompartido = AppSession.userSession.idRepublica,
                        compra = compra.value
                    )
                )
            } catch (e: IOException) {
                deleteBillCardMessage.value = "Network error: ${e.message}"
                return@launch
            } catch (e: HttpException) {
                deleteBillCardMessage.value = "Server error: ${e.message}"
                return@launch
            }
            if (response.isSuccessful) {
                delay(2000)
                navController.navigate("home")
                deleteBillCardMessage.value = "Successful deletion!"
            }
        }
    }
}

