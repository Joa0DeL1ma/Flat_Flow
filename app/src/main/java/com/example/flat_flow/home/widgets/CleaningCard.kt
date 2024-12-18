package com.example.flat_flow.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.flat_flow.domain.CleaningCards
import com.example.flat_flow.viewModel.DeleteCleaningCardViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun CleaningCard(
    task: CleaningCards,
    viewModel: DeleteCleaningCardViewModel,
    navController: NavHostController,
) {
    Column(
        modifier =
        Modifier
            .clickable(
                enabled = viewModel.clickableCleaningCard.value,
                onClick = {
                    viewModel.idCalendario.value = task.idCalendario;
                    viewModel.deleteCleaningCard(navController)
                })
            .clip(RoundedCornerShape(10.dp))
            .background(Color.LightGray)
            .height(120.dp)
            .width(140.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            text = task.quehacer
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            text = task.diaVencimiento.toString())
    }
}
