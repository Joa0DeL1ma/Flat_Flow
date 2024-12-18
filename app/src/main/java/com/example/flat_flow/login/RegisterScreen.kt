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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
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
import com.example.flat_flow.viewModel.RegisterViewModel

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = viewModel(),
) {
    val nombre by viewModel.nombre.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.contraseña.collectAsState()
    val repeatPassword by viewModel.repeatPassword.collectAsState()
    val enableRegisterButton by viewModel.enableRegisterButton.collectAsState()
    val enableMinCharAlert by viewModel.enableMinCharAlert.collectAsState()
    val enablePasswordWontMatchAlert by viewModel.enablePasswordWontMatchAlert.collectAsState()
    val toastMessage by viewModel.toastMessage.collectAsState()
    val context = LocalContext.current

    // Focus Requesters para navegação entre campos
    val emailFocusRequester = FocusRequester()
    val passwordFocusRequester = FocusRequester()
    val repeatPasswordFocusRequester = FocusRequester()

    if (toastMessage.isNotEmpty()) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        viewModel.clearToastMessage()
    }

    Column(
        modifier = Modifier
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
                contentDescription = "",
                tint = Color.White,
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = nombre,
                onValueChange = viewModel::onNombreChange,
                label = { Text(text = "Name") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { emailFocusRequester.requestFocus() }
                )
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(emailFocusRequester),
                value = email,
                onValueChange = viewModel::onEmailChange,
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { passwordFocusRequester.requestFocus() }
                ),
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(passwordFocusRequester),
                value = password,
                onValueChange = viewModel::onPasswordChange,
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { repeatPasswordFocusRequester.requestFocus() }
                ),
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(repeatPasswordFocusRequester),
                value = repeatPassword,
                onValueChange = viewModel::onRepeatPasswordChange,
                label = { Text(text = "Repeat Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { /* Aqui pode-se chamar uma ação, como validação */ }
                ),
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
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    disabledContentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                ),
                elevation = ButtonDefaults.buttonElevation(8.dp),
                shape = RoundedCornerShape(6.dp),
                onClick = { viewModel.register(navController) },
            ) {
                Text(fontSize = 16.sp, text = "Register Account")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
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

@Preview
@Composable
private fun RegisterScreenPreview() {
    Flat_FlowTheme {
        RegisterScreen(navController = rememberNavController())
    }
}
