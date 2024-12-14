package com.example.flat_flow.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.flat_flow.AppSession
import com.example.flat_flow.domain.FetchMembersUseCase
import com.example.flat_flow.domain.Members
import com.example.flat_flow.model.data.QuitRepublicRequest
import com.example.flat_flow.model.data.api.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MembersScreenViewModel(private val fetchMembersUseCase: FetchMembersUseCase) : ViewModel() {
    private val _members = mutableStateOf<List<Members>>(emptyList())
    val members: State<List<Members>> = _members
    var quitRepublicMessage: MutableState<String> = mutableStateOf("")

    init {
        loadMembers()
    }

    private fun loadMembers() {
        viewModelScope.launch {
            try {
                val members = fetchMembersUseCase()
                Log.d("MembersScreenViewModel", "Members carregados: $members") // Log com TAG
                _members.value = members
            } catch (e: Exception) {
                Log.e(
                    "MembersScreenViewModel",
                    "Erro ao carregar os dados: ${e.message}",
                    e
                ) // Log de erro com TAG
            }
        }
    }

    fun Logout() {
        AppSession.userSession.idUsuario = 0
        AppSession.userSession.idRepublica = 0
        AppSession.userSession.idRepublicaInserido = ""
    }

    fun quitRepublic(navController: NavController) {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.api.quitRepublic(
                    QuitRepublicRequest(
                        idUsuarios = AppSession.userSession.idUsuario,
                        PisoCompartido_idPisoCompartido = AppSession.userSession.idRepublica
                    )
                )
            } catch (e: IOException) {
                quitRepublicMessage.value = "Network error: ${e.message}"
                return@launch
            } catch (e: HttpException) {
                quitRepublicMessage.value = "Server error: ${e.message}"
                return@launch
            }
            if (response.isSuccessful) {
                val body = response.body()
                quitRepublicMessage.value = "Successful quit republic!"
                if (body != null) {
                    AppSession.userSession.idRepublica = 0
                    AppSession.userSession.idRepublicaInserido = ""
                    navController.navigate("loading/1000/enterRepublic"){
                        popUpTo("enterRepublic") { inclusive = true }
                    }
                }
            } else {
                quitRepublicMessage.value = "quit republic failed: ${response.message()}"
            }
        }
    }
}