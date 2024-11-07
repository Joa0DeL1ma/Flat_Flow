package com.example.flat_flow.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.flat_flow.R
import com.example.flat_flow.ui.theme.Flat_FlowTheme

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = viewModel(),
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val repeatPassword by viewModel.repeatPassword.collectAsState()
    val enableRegisterButton by viewModel.enableRegisterButton.collectAsState()
    val enableMinCharAlert by viewModel.enableMinCharAlert.collectAsState()
    val enablePasswordWontMatchAlert by viewModel.enablePasswordWontMatchAlert.collectAsState()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Color(color = 0xff005BC5))
                .padding(horizontal = 48.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        // Seu código da interface permanece o mesmo, mas agora os eventos chamam funções do ViewModel
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                modifier = Modifier.size(130.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "",
                tint = Color.White,
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = viewModel::onEmailChange,
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = viewModel::onPasswordChange,
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = repeatPassword,
                onValueChange = viewModel::onRepeatPasswordChange,
                label = { Text(text = "Repeat Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
        }
        if (enableMinCharAlert) {
            Text(
                modifier = Modifier.padding(top = 4.dp),
                color = Color.Yellow,
                text = "* Minimum 8 char`s",
            )
        }
        if (enablePasswordWontMatchAlert) {
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "* Passwords do not match",
                color = Color.Yellow,
            )
        }
        Column(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = enableRegisterButton,
                colors =
                    ButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black,
                        disabledContentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                    ),
                elevation = ButtonDefaults.buttonElevation(8.dp),
                shape = RoundedCornerShape(6.dp),
                onClick = {
                    if (enableRegisterButton) {
                        navController.navigate("loading/2000/enterRepublic")
                    }
                },
            ) {
                Text(fontSize = 16.sp, text = "Register Account")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors =
                    ButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black,
                        disabledContentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                    ),
                elevation = ButtonDefaults.buttonElevation(8.dp),
                shape = RoundedCornerShape(6.dp),
                onClick = { navController.navigate("loading/1000/login") },
            ) {
                Text(fontSize = 16.sp, text = "Login")
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
private fun RegisterScreenPreview() {
    Flat_FlowTheme {
        RegisterScreen(navController = rememberNavController())
    }
}
