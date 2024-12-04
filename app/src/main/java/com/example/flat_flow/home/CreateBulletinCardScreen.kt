package com.example.flat_flow.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.flat_flow.home.widgets.HomeTopAppBar
import com.example.flat_flow.viewModel.CreateBulletinCardViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun CreateBulletinCardScreen(
    navController: NavHostController,
    viewModel: CreateBulletinCardViewModel = viewModel()
) {

    Column(
        modifier =
        Modifier
            .background(Color(0xff005BC5))
            .fillMaxSize()
            .padding(start = 16.dp, top = 12.dp, end = 16.dp),
    ) {
        HomeTopAppBar(
            modifier =
            Modifier.padding(
                top = 6.dp,
                bottom = 16.dp,
            ),
        )
        Column(
            modifier =
            Modifier
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp))
                .fillMaxWidth()
                .heightIn(min = 300.dp)
                .background(Color.White)
                .padding(16.dp),
        ) {
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Bulletin board card creation",
                )
            }
            Text(
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                fontWeight = FontWeight.Bold,
                text = "Insert content:",
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                colors =
                OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    unfocusedBorderColor = Color(0xff005BC5),
                    focusedContainerColor = Color.White,
                    focusedBorderColor = Color(0xff005BC5),
                ),
                value = viewModel.informaciones.value,
                onValueChange = { viewModel.informaciones.value = it },
                placeholder = { Text(color = Color.LightGray, text = "Card content...") },
            )
            Button(
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                colors =
                ButtonColors(
                    containerColor = Color(0xff005BC5),
                    contentColor = Color.White,
                    disabledContentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                ),
                onClick = {
                    viewModel.createBulletinCard(navController)
                },
                enabled = viewModel.informaciones.value.isNotEmpty()
            ) {
                Text(text = "Create card")
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
fun CreateBulletinCardScreenPreview() {
    CreateBulletinCardScreen(navController = rememberNavController())
}
