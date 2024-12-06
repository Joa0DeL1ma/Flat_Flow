package com.example.flat_flow.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.flat_flow.R
import com.example.flat_flow.domain.FetchBillCardsUseCase
import com.example.flat_flow.domain.FetchBulletinCardsUseCase
import com.example.flat_flow.domain.FetchCleaningCardsUseCase
import com.example.flat_flow.home.widgets.BillCard
import com.example.flat_flow.home.widgets.BulletinCard
import com.example.flat_flow.home.widgets.CleaningCard
import com.example.flat_flow.home.widgets.HomeTopAppBar
import com.example.flat_flow.model.data.BillCardRepository
import com.example.flat_flow.model.data.BulletinCardRepository
import com.example.flat_flow.model.data.CleaningCardRepository
import com.example.flat_flow.model.data.api.RetrofitInstance
import com.example.flat_flow.viewModel.BillCardViewModel
import com.example.flat_flow.viewModel.BillCardViewModelFactory
import com.example.flat_flow.viewModel.BulletinCardViewModel
import com.example.flat_flow.viewModel.BulletinCardViewModelFactory
import com.example.flat_flow.viewModel.CleaningCardViewModel
import com.example.flat_flow.viewModel.CleaningCardViewModelFactory
import com.example.flat_flow.viewModel.DeleteBillCardViewModel
import com.example.flat_flow.viewModel.DeleteBulletinCardViewModel
import com.example.flat_flow.viewModel.DeleteCleaningCardViewModel


@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(
    deleteBulletinCardViewModel: DeleteBulletinCardViewModel = DeleteBulletinCardViewModel(),
    deleteBillCardViewModel: DeleteBillCardViewModel = DeleteBillCardViewModel(),
    deleteCleaningCardViewModel: DeleteCleaningCardViewModel = DeleteCleaningCardViewModel()
) {
    val fetchBulletinCardsUseCase =
        FetchBulletinCardsUseCase(BulletinCardRepository(RetrofitInstance.api))
    val fetchCleaningCardsUseCase =
        FetchCleaningCardsUseCase(CleaningCardRepository(RetrofitInstance.api))
    val bulletinFactory = BulletinCardViewModelFactory(fetchBulletinCardsUseCase)
    val cleaningFactory = CleaningCardViewModelFactory(fetchCleaningCardsUseCase)
    val bulletinCardViewModel: BulletinCardViewModel = viewModel(factory = bulletinFactory)
    val cleaningCardViewModel: CleaningCardViewModel = viewModel(factory = cleaningFactory)
    val fetchBillCardsUseCase = FetchBillCardsUseCase(BillCardRepository(RetrofitInstance.api))
    val billFactory = BillCardViewModelFactory(fetchBillCardsUseCase)
    val billCardViewModel: BillCardViewModel = viewModel(factory = billFactory)
    val bulletinCards by bulletinCardViewModel.bulletinCards
    val cleaningCards by cleaningCardViewModel.cleaningCards
    val billCards by billCardViewModel.billCards

    Column(
        modifier =
        Modifier
            .background(Color(0xff005BC5))
            .fillMaxSize()
            .padding(start = 16.dp, top = 12.dp, end = 16.dp),
    ) {
        HomeTopAppBar(
            modifier = Modifier.padding(
                top = 6.dp,
                bottom = 16.dp
            )
        )
        Column(
            modifier =
            Modifier
                .clip(RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp))
                .fillMaxWidth()
                .heightIn(min = 300.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                IconButton(
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = "Touch to delete card from bulletin board",
                            tint = Color.Gray,
                        )
                    },
                    onClick = { deleteBulletinCardViewModel.toggleClickableBulletinCard()},
                )
                Text(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Bulletin Board",
                )
                IconButton(
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = "Touch to add a card to the bulletin board",
                            tint = Color.Gray,
                        )
                    },
                    onClick = { /*TODO*/ }
                )
            }
            if (bulletinCards.isEmpty()) {
                Text(text = "Sem dados no Bulletin Board.")
            } else {
                LazyColumn {
                    items(bulletinCards) { card ->
                        BulletinCard(card, viewModel = deleteBulletinCardViewModel)
                    }
                }
            }
            // Cleaning Calendar
            Column(
                modifier =
                Modifier
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .heightIn(150.dp)
                    .fillMaxWidth()
                    .background(Color.White),
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Cleaning Calendar"
                    )
                    Row {
                        IconButton(
                            content = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_delete),
                                    contentDescription = "Touch to delete card from cleaning board",
                                    tint = Color.Gray,
                                )
                            },
                            onClick = { deleteCleaningCardViewModel.toggleClickableCleaningCard() }
                        )
                        IconButton(
                            content = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_add),
                                    contentDescription = "Touch to add a card to the cleaning board",
                                    tint = Color.Gray,
                                )
                            },
                            onClick = { /*TODO*/ }
                        )
                    }
                }
                if (cleaningCards.isEmpty()) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = "Sem dados no Cleaning Board."
                    )
                } else {
                    LazyRow(
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(cleaningCards) { card ->
                            CleaningCard(card, viewModel = deleteCleaningCardViewModel)
                        }
                    }
                }
                //Bills Calendar
                Column(
                    modifier =
                    Modifier
                        .padding(top = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .heightIn(150.dp)
                        .fillMaxWidth()
                        .background(Color.White),
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Bills Calendar"
                        )
                        Row {
                            IconButton(
                                content = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_delete),
                                        contentDescription = "Touch to delete card from bulletin board",
                                        tint = Color.Gray,
                                    )
                                },
                                onClick = { deleteBillCardViewModel.toggleClickableBillCard() },
                            )
                            IconButton(
                                content = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_add),
                                        contentDescription = "Touch to add a card to the bulletin board",
                                        tint = Color.Gray,
                                    )
                                },
                                onClick = { /*TODO*/ },
                            )
                        }
                    }
                    if (billCards.isEmpty()) {
                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = "Sem dados no Bill Board."
                        )
                    } else {
                        LazyRow(
                            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(billCards) { card ->
                                BillCard(card, viewModel = deleteBillCardViewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        deleteBulletinCardViewModel = TODO()
    )
}
