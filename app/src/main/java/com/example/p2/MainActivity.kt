package com.example.p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.p2.ui.screens.* // Importa todas las pantallas del paquete screens
import com.example.p2.ui.theme.P2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            P2Theme {
                P2App()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun P2App() {
    // ESTADO: Recuerda qué pantalla está seleccionada actualmente
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    // COMPONENTE ADAPTATIVO: Cambia el menú según el tamaño de la pantalla
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            // Recorremos nuestra lista de destinos para crear los botones
            AppDestinations.entries.forEach { destino ->
                item(
                    icon = { Icon(painterResource(destino.icon), contentDescription = null) },
                    label = { Text(destino.label) },
                    selected = destino == currentDestination,
                    onClick = { currentDestination = destino },
                )
            }
        }
    ) {
        // LÓGICA DE INTERCAMBIO: Muestra el contenido según el estado actual
        when (currentDestination) {
            AppDestinations.HOME -> HomeContent()
            AppDestinations.FAVORITES -> FavoritesContent()
            AppDestinations.PROFILE -> ProfileContent()
        }
    }
}
