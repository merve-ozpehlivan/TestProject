package com.example.inventivtestcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.inventivtestcase.navigation.AppNavHost
import com.example.inventivtestcase.navigation.Routes
import com.example.inventivtestcase.ui.theme.*

import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InventivTestCaseTheme {
                val navController = rememberNavController()
                val systemUiController = rememberSystemUiController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStack?.destination?.route

                SideEffect {
                    systemUiController.setStatusBarColor(color = Yesil, darkIcons = false)
                }

                val canGoBack = navController.previousBackStackEntry != null

                Scaffold(
                    topBar = {
                        TopAppBar(
                            backgroundColor = Yesil,
                            contentColor = Beyaz,
                            elevation = 4.dp,
                            title = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = if (canGoBack) 48.dp else 0.dp)
                                ) {
                                    Text(
                                        text = when {
                                            currentRoute?.startsWith(Routes.CARD_DETAIL) == true -> "Card Detail"
                                            else -> "Card List"
                                        },
                                        modifier = Modifier.align(Alignment.Center),
                                        textAlign = TextAlign.Center,
                                        fontSize = 27.sp,
                                        color = Beyaz
                                    )
                                }
                            },
                            navigationIcon = if (canGoBack) {
                                {
                                    IconButton(onClick = {
                                        navController.popBackStack()
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.chevron_left_svgrepo_com),
                                            contentDescription = "Geri",
                                            tint = Beyaz
                                        )
                                    }
                                }
                            } else null
                        )
                    },
                    backgroundColor = AcikYesil
                ) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InventivTestCaseTheme {

    }
}
