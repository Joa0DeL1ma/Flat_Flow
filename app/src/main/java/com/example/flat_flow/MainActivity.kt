package com.example.flat_flow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flat_flow.home.CreateBillCardScreen
import com.example.flat_flow.home.CreateBulletinCardScreen
import com.example.flat_flow.home.CreateCleaningCardScreen
import com.example.flat_flow.home.HomeScreen
import com.example.flat_flow.home.LogoutScreen
import com.example.flat_flow.home.MembersScreen
import com.example.flat_flow.login.EnterRepublicScreen
import com.example.flat_flow.login.LoadingScreen
import com.example.flat_flow.login.LoginScreen
import com.example.flat_flow.login.RegisterScreen
import com.example.flat_flow.ui.theme.Flat_FlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Flat_FlowTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("home") { HomeScreen(navController) }
                    composable("login") { LoginScreen(navController) }
                    composable("register") { RegisterScreen(navController) }
                    composable("enterRepublic") { EnterRepublicScreen(navController) }
                    composable("createBillCard") { CreateBillCardScreen(navController) }
                    composable("createBulletinCard") { CreateBulletinCardScreen(navController) }
                    composable("createCleaningCard") { CreateCleaningCardScreen(navController) }
                    composable("membersScreen") { MembersScreen(navController) }
                    composable("logoutScreen") { LogoutScreen(navController) }

                    // Rota para a tela de loading com parâmetros
                    composable("loading/{time}/{destination}") { backStackEntry ->
                        // Recupera os parâmetros da navegação
                        val time = backStackEntry.arguments?.getString("time")?.toIntOrNull() ?: 0
                        val destination = backStackEntry.arguments?.getString("destination") ?: "login"

                        // Chama a tela de loading passando os parâmetros
                        LoadingScreen(
                            navController = navController,
                            time = time,
                            destination = destination
                        )
                    }
                }
            }
        }
    }
}
