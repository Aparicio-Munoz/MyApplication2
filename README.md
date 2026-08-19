# P2App

Proyecto de ejemplo en **Jetpack Compose** que muestra cómo implementar
navegación adaptativa con `NavigationSuiteScaffold` (Material 3 Adaptive):

- 📱 **Celular** → barra de navegación inferior.
- 📟 **Tablet / plegable** → riel de navegación lateral.
- 🖥️ **Escritorio / pantalla expandida** → panel de navegación lateral.

Cada pantalla (Inicio, Favoritos, Perfil) vive en su propio archivo dentro
de `ui/screens/`. El destino seleccionado se conserva ante rotaciones de
pantalla gracias a `rememberSaveable`.

## Requisitos

- Android Studio (versión reciente, con soporte para AGP 9.x / Kotlin 2.2.x).
- JDK 11 o superior (gestionado normalmente por el propio Android Studio).
- SDK de Android con la plataforma **API 37** instalada (se puede instalar
  desde el SDK Manager si no la tienes).

## Cómo abrir el proyecto

1. Descomprime el archivo `P2App.zip` en la carpeta donde quieras guardar el proyecto.
2. Abre Android Studio.
3. Selecciona **File > Open...** (u **Open** desde la pantalla de bienvenida).
4. Navega hasta la carpeta `P2App` descomprimida (la que contiene
   `settings.gradle.kts`) y ábrela.
5. Espera a que Gradle sincronice el proyecto (puede tardar unos minutos la
   primera vez, ya que descargará las dependencias).
6. Ejecuta la app con el botón ▶️ **Run** sobre un emulador o dispositivo
   físico (minSdk 24).

> Nota: `local.properties` no se incluye en el zip porque contiene la ruta
> del SDK de Android específica de cada máquina. Android Studio lo genera
> automáticamente al abrir el proyecto y detectar el SDK instalado.

## Estructura del proyecto

```
app/src/main/java/com/example/p2/
├── MainActivity.kt                # Controlador principal (P2App() con NavigationSuiteScaffold)
└── ui/
    ├── theme/
    │   ├── Color.kt
    │   ├── Type.kt
    │   └── Theme.kt                # P2Theme, con soporte de dynamic color en API 31+
    └── screens/
        ├── AppDestinations.kt      # Definición del menú (HOME, FAVORITES, PROFILE)
        ├── HomeScreen.kt           # HomeContent(): pantalla de Inicio
        ├── FavoritesScreen.kt      # FavoritesContent(): pantalla de Favoritos
        └── ProfileScreen.kt        # ProfileContent(): pantalla de Perfil

app/src/main/res/drawable/
├── ic_home.xml
├── ic_favorite.xml
└── ic_account_box.xml             # vector drawables referenciados por AppDestinations
```

## Dependencias clave (`app/build.gradle.kts`)

- `platform(libs.androidx.compose.bom)` → BOM `2026.08.00` (la más reciente
  estable, verificada en Google Maven).
- `androidx.compose.material3:material3-adaptive-navigation-suite` (sin
  versión explícita: la resuelve el propio BOM → `1.4.0`).
- Plugin `org.jetbrains.kotlin.plugin.compose` para el compilador de Compose.

### Nota sobre `compileSdk`/`targetSdk`

Se usa **API 37** en vez de 35. El compose-bom más reciente arrastra
librerías (compose-ui, foundation, material3-adaptive, activity, core-ktx)
que exigen compilar contra API 37 o superior; con `compileSdk 35` el build
falla en la tarea `checkDebugAarMetadata` con errores de metadata de AAR
(no son errores de código). Se priorizó "BOM más reciente + proyecto que
compila" por sobre el número de SDK original, ya que ambos requisitos eran
incompatibles entre sí. `minSdk` se mantiene en 24.

## Verificación realizada

- `./gradlew :app:assembleDebug` → `BUILD SUCCESSFUL`, sin warnings.
- `./gradlew :app:testDebugUnitTest` → `BUILD SUCCESSFUL`.
- Instalado y ejecutado en un dispositivo físico real (Android 13) vía adb:
  arranca sin crashes y las tres pestañas (Inicio, Favoritos, Perfil)
  cambian de pantalla correctamente.
