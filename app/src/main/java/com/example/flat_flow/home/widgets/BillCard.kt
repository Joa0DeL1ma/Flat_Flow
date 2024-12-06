package com.example.flat_flow.home.widgets

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flat_flow.domain.BillCards
import com.example.flat_flow.viewModel.DeleteBillCardViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun BillCard(
    card: BillCards,
    viewModel: DeleteBillCardViewModel
) {
    Column(
        modifier =
        Modifier
            .clickable(
                enabled = viewModel.clickableBillCard.value,  onClick = { Log.d("BillCard", "CARD CLICADOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO ${card.compra}") })
            .clip(RoundedCornerShape(10.dp))
            .background(Color.LightGray)
            .height(120.dp)
            .width(140.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp, text = card.compra
            )
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                text = card.diaVencimiento,
            )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "$" + card.valor)
    }
}

@Preview(showBackground = true)
@Composable
fun BillCardPreview() {
    BillCard(
        card = BillCards(
            diaVencimiento = "10/10/2023",
            valor = "200",
            compra = "Luz"
        ),
        viewModel = TODO()
    )
}

