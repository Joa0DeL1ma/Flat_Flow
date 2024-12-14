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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.flat_flow.R
import com.example.flat_flow.domain.FetchMembersUseCase
import com.example.flat_flow.home.widgets.HomeTopAppBar
import com.example.flat_flow.home.widgets.MembersItem
import com.example.flat_flow.model.data.MembersRepository
import com.example.flat_flow.model.data.api.RetrofitInstance
import com.example.flat_flow.viewModel.MembersScreenViewModel
import com.example.flat_flow.viewModel.MembersScreenViewModelFactory

@Suppress("ktlint:standard:function-naming")
@Composable
fun MembersScreen(
    navController: NavHostController
) {
    val fetchMembersUseCase =
        FetchMembersUseCase(MembersRepository(RetrofitInstance.api))
    val membersFactory = MembersScreenViewModelFactory(fetchMembersUseCase)
    val membersScreenViewModel: MembersScreenViewModel = viewModel(factory = membersFactory)
    val members by membersScreenViewModel.members

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
            navController = navController
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
                IconButton(
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Go back",
                            tint = Color.Gray,
                        )
                    },
                    onClick = { navController.navigate("home") },
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp, end = 20.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Republic participants list",
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(members) { member ->
                    MembersItem(
                        member
                    )
                }
            }

        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
fun MembersScreenPreview() {
    MembersScreen(navController = rememberNavController())
}
