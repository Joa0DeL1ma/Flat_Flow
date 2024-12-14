package com.example.flat_flow.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.flat_flow.domain.BulletinCards
import com.example.flat_flow.viewModel.DeleteBulletinCardViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun BulletinCard(
    card: BulletinCards,
    viewModel: DeleteBulletinCardViewModel,
    navController: NavHostController
) {

    Column(
        modifier =
        Modifier
            .clickable(
                enabled = viewModel.clickableBulletinCard.value,
                onClick = { viewModel.idMuro.value = card.idMuro; viewModel.deleteBulletinCard(navController) })
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xff005BC5))
            .heightIn(min = 80.dp)
            .widthIn(min = 160.dp, max = 220.dp)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            text = card.informaciones
        )
        Spacer(Modifier.height(8.dp))
        Text(
            color = Color.White,
            fontSize = 10.sp,
            text = "- "+card.nombre
        )

    }
    Spacer(Modifier.height(8.dp))
}

@Preview(showBackground = true)
@Composable
fun BulletinCardPreview() {
    BulletinCard(
        card = BulletinCards(
            informaciones = "Lava as cueca fulano, ta fedendo a casa toda.",
            idMuro = 1,
            nombre = "Jose"
        ),
        viewModel = TODO(),
        navController = TODO()
    )
}