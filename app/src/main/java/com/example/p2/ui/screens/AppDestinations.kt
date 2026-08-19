package com.example.p2.ui.screens

import com.example.p2.R

enum class AppDestinations(
    val label: String, // El nombre que aparecerá bajo el icono
    val icon: Int,     // El recurso del icono (debe estar en res/drawable)
) {
    HOME("Inicio", R.drawable.ic_home),
    FAVORITES("Favoritos", R.drawable.ic_favorite),
    PROFILE("Perfil", R.drawable.ic_account_box)
}
