package com.jcl.lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jcl.lab5.ui.theme.Lab5Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Pantalla1(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun Pantalla1(modifier: Modifier = Modifier) {
    //Columna principal de la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        //Fila celeste
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
            //Color celeste
        )
        {
            //Icono azul
            //texto de actualización disponible
            //Texto de Descargar con link a playStore
        }

        //Fila con fecha de cumpleaños y botón de terminar jornada
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        )
        {
            //Texto de viernes
            //Texto de fecha de cumpleaños
            //Botón de terminar jornada
        }

        //Caja para información del restaurante
        Card(
            modifier = Modifier
                .fillMaxSize()
        )
        {
            Column(
                modifier = Modifier
            )
            {
                // Columna para los textos del restaurante
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    // Nombre
                    // Dirección
                    // Horarios
                }

                // Fila inferior para los botones
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Botón Iniciar
                    // Botón Detalles
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun Pantalla1Preview() {
    Lab5Theme {
        Pantalla1()
    }
}