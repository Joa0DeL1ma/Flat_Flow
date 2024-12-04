package com.example.flat_flow.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.flat_flow.R
import com.example.flat_flow.viewModel.RepublicEnterViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun EnterRepublicScreen(
    navController: NavHostController,
    viewModel: RepublicEnterViewModel = viewModel(),
) {
    val insertCodeError by remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }

    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color(color = 0xff005BC5))
            .padding(horizontal = 48.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
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
                    value = viewModel.idRepublicaInserido.value,
                    onValueChange = { newValue ->
                        viewModel.idRepublicaInserido.value = newValue },
                    label = { Text(text = "Code...") },
                    trailingIcon = {
                        IconButton(
                            onClick = { showDialog.value = true },
                            content = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_login),
                                    contentDescription = "",
                                )
                            },
                        )
                    },
                )
            }
            if (insertCodeError) {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "* Enter correct code",
                    color = Color.Yellow,
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black,
                        disabledContentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                    ),
                    elevation = ButtonDefaults.buttonElevation(8.dp),
                    shape = RoundedCornerShape(6.dp),
                    onClick = {
                        viewModel.republicEnter(navController)
                    },
                    enabled = viewModel.idRepublicaInserido.value.isNotBlank(),
                ) {
                    Text(fontSize = 16.sp, text = "Enter")
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black,
                        disabledContentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                    ),
                    elevation = ButtonDefaults.buttonElevation(8.dp),
                    shape = RoundedCornerShape(6.dp),
                    onClick = { showDialog.value = true }, // Exibe o popup
                    enabled = viewModel.idRepublicaInserido.value.isNotBlank()
                ) {
                    Text(fontSize = 16.sp, text = "Create republic")
                }
            }
        }
    }
    // Componente do AlertDialog
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Create Republic") },
            text = { Text("Are you sure you want to create a new republic?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog.value = false
                        // Ação confirmada: adicionar lógica aqui
                        viewModel.createRepublic(navController)
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog.value = false }
                ) {
                    Text("No")
                }
            }
        )
    }
}



@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
private fun EnterRepublicScreenPreview() {
    MaterialTheme {
        EnterRepublicScreen(navController = rememberNavController())
    }
}
