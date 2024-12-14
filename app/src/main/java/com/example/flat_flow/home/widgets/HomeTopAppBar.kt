package com.example.flat_flow.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.flat_flow.AppSession
import com.example.flat_flow.R

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeTopAppBar(modifier: Modifier = Modifier, navController: NavHostController) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(
            modifier = Modifier.size(64.dp),
            onClick = { navController.navigate("logoutScreen") },
            content = {
                Icon(
                    modifier = Modifier.size(56.dp),
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Logo",
                    tint = Color.White
                )
            },
        )
        Row(
            Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                color = Color(0xff005BC5),
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 8.dp),
                text = "Code: ",
                fontSize = 20.sp,
            )
            Text(
                fontWeight = FontWeight.Bold,
                color = Color(0xff005BC5),
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, end = 8.dp),
                text = AppSession.userSession.idRepublicaInserido,
                fontSize = 20.sp,
            )
        }
        IconButton(
            modifier = Modifier.size(64.dp),
            onClick = { navController.navigate("membersScreen") },
            content = {
                Icon(
                    modifier = Modifier.size(56.dp),
                    painter = painterResource(id = R.drawable.ic_group),
                    contentDescription = "Participants",
                    tint = Color.White
                )
            },
        )
    }
}

@Preview(showBackground = false)
@Composable
fun HomeTopAppBarPreview() {
    val navController = rememberNavController() // Create a mock NavHostController
    // ... other mocking code ...
    HomeTopAppBar(modifier = Modifier, navController = navController)
    // ... restore original values ...
}
