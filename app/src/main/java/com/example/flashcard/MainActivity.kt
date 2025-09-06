package com.example.myfirstapplicationhelloworld  // Assure-toi que le chemin correspond au répertoire du fichier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border  // Ajouté l'import pour la fonction border
import androidx.compose.foundation.clickable  // Ajouté l'import pour la fonction clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Data class pour une flashcard
data class Flashcard(val question: String, val answer: String)

@Composable
fun FlashcardAppTheme(content: @Composable () -> Unit) {
    // Theme de base avec Material3
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF6200EE),  // Couleur vive pour le bouton et accents
            secondary = Color(0xFF03DAC6), // Couleur secondaire
            background = Color(0xFFF1F1F1), // Fond plus clair
            surface = Color.White
        ),
        typography = Typography(),
        content = content
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlashcardAppTheme {
                FlashcardScreen()
            }
        }
    }
}

@Composable
fun FlashcardScreen() {
    val flashcards = listOf(
        Flashcard("Quel est le capital de la France ?", "Paris"),
        Flashcard("Combien de continents existe-t-il ?", "7"),
        Flashcard("Qui a écrit 'Les Misérables' ?", "Victor Hugo")

    )

    var currentCardIndex by remember { mutableIntStateOf(0) }
    var showAnswer by remember { mutableStateOf(false) }

    val currentCard = flashcards[currentCardIndex]

    // Affichage de la carte avec la question et la réponse
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFFF0000)) // Fond de l'écran avec une couleur vive
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Affichage de la carte avec la question
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(50.dp)
                .background(Color(0xFFFFC107), shape = MaterialTheme.shapes.medium) // Carte colorée
                .clickable { showAnswer = !showAnswer } // On change l'état pour montrer la réponse
                .border(2.dp, Color(0xFF6200EE), shape = MaterialTheme.shapes.medium) // Bordure colorée
        ) {
            Text(
                text = if (showAnswer) currentCard.answer else currentCard.question,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Affichage des boutons "Précédent" et "Suivant"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    currentCardIndex = (currentCardIndex - 1 + flashcards.size) % flashcards.size
                    showAnswer = false
                },
                modifier = Modifier
                    .background(Color(0xFF6200EE)) // Couleur du bouton
                    .padding(16.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Précédent", color = Color.White)
            }

            Button(
                onClick = {
                    currentCardIndex = (currentCardIndex + 1) % flashcards.size
                    showAnswer = false
                },
                modifier = Modifier
                    .background(Color(0xFF6200EE)) // Couleur du bouton
                    .padding(16.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Suivant", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FlashcardAppTheme {
        FlashcardScreen()
    }
}
