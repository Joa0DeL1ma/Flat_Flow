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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.flat_flow.viewModel.CreateBillCardViewModel
import com.example.flat_flow.viewModel.CreateCleaningCardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun CreateCleaningCardScreen(
    navController: NavHostController,
    viewModel: CreateCleaningCardViewModel = viewModel()
) {
    var expandedRecurrence by remember { mutableStateOf(false) }
    var expandedWeek by remember { mutableStateOf(false) }
    var expandedMonth by remember { mutableStateOf(false) }

    val recurrenceOptions = listOf("Monthly", "Weekly")
    val dayOfTheWeek =
        listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
    val dayOfTheMonth = (1..31).map { it.toString() }

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
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Cleaning calendar card creation",
                )
            }

            // TODO RECURRENCE

            Text(
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                fontWeight = FontWeight.Bold,
                text = "Select Recurrence",
            )
            ExposedDropdownMenuBox(
                expanded = expandedRecurrence,
                onExpandedChange = {
                    expandedRecurrence = !expandedRecurrence
                },
            ) {
                OutlinedTextField(
                    colors =
                    OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        unfocusedBorderColor = Color(0xff005BC5),
                        focusedContainerColor = Color.White,
                        focusedBorderColor = Color(0xff005BC5),
                    ),
                    value = viewModel.recurrence.value,
                    onValueChange = { newValue ->
                        viewModel.recurrence.value = newValue
                    },
                    readOnly = true,
                    placeholder = { Text("Monthly, weekly...", color = Color.LightGray) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedRecurrence)
                    },
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                )

                ExposedDropdownMenu(
                    expanded = expandedRecurrence,
                    onDismissRequest = { expandedRecurrence = false },
                ) {
                    recurrenceOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                viewModel.recurrence.value = option
                                expandedRecurrence = false
                            },
                        )
                    }
                }
            }

            // TODO DAY OF THE WEEK
            if (viewModel.recurrence.value == "Weekly") {
                Text(
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    text = "Select day of the week",
                )
                ExposedDropdownMenuBox(
                    expanded = expandedWeek,
                    onExpandedChange = {
                        expandedWeek = !expandedWeek
                    },
                ) {
                    OutlinedTextField(
                        colors =
                        OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            unfocusedBorderColor = Color(0xff005BC5),
                            focusedContainerColor = Color.White,
                            focusedBorderColor = Color(0xff005BC5),
                        ),
                        value = viewModel.dayOfTheWeek.value?:"",
                        onValueChange = {},
                        readOnly = true,
                        placeholder = { Text("Sunday, monday...", color = Color.LightGray) },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedWeek)
                        },
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                    )

                    ExposedDropdownMenu(
                        expanded = expandedWeek,
                        onDismissRequest = { expandedWeek = false },
                    ) {
                        dayOfTheWeek.forEach { dayOfTheWeek ->
                            DropdownMenuItem(
                                text = { Text(dayOfTheWeek) },
                                onClick = {
                                    viewModel.dayOfTheWeek.value = dayOfTheWeek
                                    expandedWeek = false
                                },
                            )
                        }
                    }
                }
            }

            // TODO DAY OF THE MONTH

            if (viewModel.recurrence.value == "Monthly") {
                Text(
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    text = "Select day of the month",
                )
                ExposedDropdownMenuBox(
                    expanded = expandedMonth,
                    onExpandedChange = {
                        expandedMonth = !expandedMonth
                    },
                ) {
                    OutlinedTextField(
                        colors =
                        OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            unfocusedBorderColor = Color(0xff005BC5),
                            focusedContainerColor = Color.White,
                            focusedBorderColor = Color(0xff005BC5),
                        ),
                        value = viewModel.numberDay.value?:"",
                        onValueChange = {},
                        readOnly = true,
                        placeholder = { Text("Monthly, weekly, daily", color = Color.LightGray) },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMonth)
                        },
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                    )

                    ExposedDropdownMenu(
                        expanded = expandedMonth,
                        onDismissRequest = { expandedMonth = false },
                    ) {
                        dayOfTheMonth.forEach { dayOfTheMonth ->
                            DropdownMenuItem(
                                text = { Text(dayOfTheMonth) },
                                onClick = {
                                    viewModel.numberDay.value = dayOfTheMonth
                                    expandedMonth = false
                                },
                            )
                        }
                    }
                }
            }

            Text(
                modifier = Modifier.padding(bottom = 8.dp, top = 16.dp),
                fontWeight = FontWeight.Bold,
                text = "Chore name:",
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
                value = viewModel.task.value,
                onValueChange = { viewModel.task.value = it },
                placeholder = {
                    Text(
                        color = Color.LightGray,
                        text = "Clean windows, wash clothes..."
                    )
                },
            )
            Text(
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                fontWeight = FontWeight.Bold,
                text = "Who will do:",
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
                value = viewModel.assigned.value,
                onValueChange = { viewModel.assigned.value = it },
                placeholder = { Text(color = Color.LightGray, text = "Kaká, Ronaldo, Romário...") },
            )
            Button(
                enabled = viewModel.assigned.value.isNotEmpty(),
                shape = RoundedCornerShape(6.dp),
                modifier =
                Modifier
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
                    viewModel.createCleaningCard(navController)
                },
            ) {
                Text(text = "Create card")
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
fun CreateCleaningCardScreenPreview() {
    CreateCleaningCardScreen(navController = rememberNavController())
}
