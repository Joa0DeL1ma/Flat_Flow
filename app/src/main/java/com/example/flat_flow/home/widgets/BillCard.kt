package com.example.flat_flow.home.widgets

import androidx.compose.foundation.background
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

@Suppress("ktlint:standard:function-naming")
@Composable
fun BillCard(card: BillCards) {
    Column(
        modifier =
        Modifier
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
                fontSize = 22.sp, text = "${card.numberDay}"
            )
        if (card.dayOfTheWeek != null) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = "${card.dayOfTheWeek}",
            )
        }
        Text(
            fontWeight = FontWeight.Bold,
            text = card.recurrence
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(text = card.billName + ": ")
        Text(text = "$" + "${card.value}")
    }
}

@Preview(showBackground = true)
@Composable
fun BillCardPreview() {
    BillCard(
        card = BillCards(
            numberDay = 25,
            dayOfTheWeek = "Friday",
            recurrence = "Monthly",
            billName = "Rent",
            value = 1500.00
        )
    )
}

