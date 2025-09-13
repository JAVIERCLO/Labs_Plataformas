package com.jcl.lab8


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import coil.request.ImageRequest


sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Characters : Screen("characters")
    data object Detail : Screen("detail/{id}") {
        fun route(id: Int) = "detail/$id"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val nav = rememberNavController()
                val db = remember { CharacterDb() }

                NavHost(navController = nav, startDestination = Screen.Login.route) {

                    composable(Screen.Login.route) {
                        LoginScreen(
                            nombre = "Javier Chávez 23132",
                            onStart = {
                                nav.navigate(Screen.Characters.route) {
                                    popUpTo(Screen.Login.route) { inclusive = true }
                                    launchSingleTop = true
                                }
                            }
                        )
                    }

                    composable(Screen.Characters.route) {
                        val activity = LocalActivity.current
                        BackHandler { activity?.finish() }
                        CharactersScreen(
                            title = "Characters",
                            characters = remember { db.getAllCharacters() },
                            onCharacterClick = { id -> nav.navigate(Screen.Detail.route(id)) }
                        )
                    }

                    composable(
                        route = Screen.Detail.route,
                        arguments = listOf(navArgument("id") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments?.getInt("id")!!
                        val character = remember(id) { db.getCharacterById(id) }
                        CharacterDetailScreen(
                            title = "Character Detail",
                            character = character,
                            onBack = { nav.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    nombre: String,
    onStart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(Modifier.height(24.dp))

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://upload.wikimedia.org/wikipedia/commons/b/b1/Rick_and_Morty.svg")
                    .build(),
                contentDescription = "Rick y Morty",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 32.dp)
                    .height(140.dp),
                contentScale = ContentScale.Fit
            )

            Button(
                onClick = onStart,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .height(48.dp)
            ) { Text("Entrar") }

            Text(
                text = nombre,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    title: String,
    characters: List<Character>,
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(title) })
        }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(characters) { c ->
                CharacterRow(
                    character = c,
                    onClick = { onCharacterClick(c.id) }
                )

            }
        }
    }
}

@Composable
private fun CharacterRow(
    character: Character,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.width(16.dp))
        Column {
            Text(
                character.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                "${character.species} - ${character.status}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

        Spacer(Modifier.width(16.dp))
        Column {
            Text(character.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text("${character.species} - ${character.status}", style = MaterialTheme.typography.bodyMedium)
        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    title: String,
    character: Character,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))

            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(16.dp))
            Text(
                character.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(24.dp))

            AttributeRow(label = "Species", value = character.species)
            AttributeRow(label = "Status", value = character.status)
            AttributeRow(label = "Gender", value = character.gender)
        }
    }
}

@Composable
private fun AttributeRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("$label:", style = MaterialTheme.typography.bodyMedium)
        Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCharactersScreen() {
    val sampleCharacters = listOf(
        Character(1, "Rick Sanchez", "Human", "Alive", "Male", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
        Character(2, "Morty Smith", "Human", "Alive", "Male", "https://rickandmortyapi.com/api/character/avatar/2.jpeg"),
        Character(3, "Summer Smith", "Human", "Alive", "Female", "https://rickandmortyapi.com/api/character/avatar/3.jpeg")
    )

    MaterialTheme {
        CharactersScreen(
            title = "Characters",
            characters = sampleCharacters,
            onCharacterClick = {}
        )
    }
}