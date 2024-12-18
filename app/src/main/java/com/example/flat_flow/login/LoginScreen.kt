package com.example.flat_flow.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.flat_flow.R
import com.example.flat_flow.viewModel.LoginViewModel


@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = viewModel()
) {
    val context = LocalContext.current

    // Criando FocusRequester para gerenciar foco
    val passwordFocusRequester = FocusRequester()

    // Observar a mensagem de login
    val loginMessage = viewModel.loginMessage.value

    // Mostrar Toast quando `loginMessage` for atualizado
    if (loginMessage.isNotEmpty()) {
        Toast.makeText(context, loginMessage, Toast.LENGTH_SHORT).show()
        viewModel.loginMessage.value = "" // Resetar após mostrar para evitar exibição repetida
    }

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color(color = 0xff005BC5))
            .padding(horizontal = 48.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                modifier = Modifier.size(130.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "FlatFlow Logo",
                tint = Color.White,
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.email.value,
                onValueChange = { newValue ->
                    viewModel.email.value = newValue
                    viewModel.updateLoginButtonState()
                },
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                        passwordFocusRequester.requestFocus()
                    }
                ),
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(passwordFocusRequester),
                value = viewModel.contraseña.value,
                onValueChange = { newValue ->
                    viewModel.contraseña.value = newValue
                    viewModel.updateLoginButtonState()
                },
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        viewModel.login(navController)
                    }
                ),
            )
        }
        if (viewModel.enableWrongLoginAlert.value) {
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "* Wrong email or password",
                color = Color.Yellow,
            )
        }
        Column(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = viewModel.enableLoginButton.value,
                colors =
                ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Gray,
                ),
                elevation = ButtonDefaults.buttonElevation(8.dp),
                shape = RoundedCornerShape(6.dp),
                onClick = {
                    viewModel.login(navController)
                },
            ) {
                Text(fontSize = 16.sp, text = "Login")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors =
                ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Gray,
                ),
                elevation = ButtonDefaults.buttonElevation(8.dp),
                shape = RoundedCornerShape(6.dp),
                onClick = { navController.navigate("loading/1000/register") },
            ) {
                Text(fontSize = 16.sp, text = "Register")
            }
        }
    }
}


@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}
