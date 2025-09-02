package com.jcl.Lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jcl.Lab7.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaConFiltros(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PantallaConFiltros( modifier: Modifier = Modifier) {
    //Columna principal
    Column(
        modifier = modifier,
    )
    {
        //Fila verde para notificaciones y flecha de volver
        Row(
            Modifier.background(Color.Green),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        )
        {
            //Icono de flecha
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Flecha para regresar"
            )
            //Texto de notificaciones
            Text(
                text = "Notificaciones",
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.White
                )
            )
        }
        //Texto de tipos de notificaciones
        Text(
            text = "Tipos de notificaciones",
            style = TextStyle(
            fontSize = 12.sp,
            color = Color.Black
            )
        )
            //Fila para los chips de filtros
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        )
        {
            //Chip de filtro para notificaciones informativas
            FilterChip(
                Text(
                    text = "Informativas"
                )
            )
            //Chip de filtro para notificaciones de capacitaciones
            FilterChip(
                Text(
                    text = "Capacitaciones"
                )
            )
        }
        //Carta para las notificaciones con cuerpo
        ElevatedCard(

        )
        {
            //Composable de notificación
        }
    }
}

//Componente para notificación
@Composable
fun Notification(modifier: Modifier = Modifier){
    //Fila principal para Icono, título, mensaje, fecha y hora
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    )
    {
        Icon(
            Icons.Default.Notifications,
            contentDescription = "Notificación informativa"
        )
        Column(

        )
        {
            //Texto de título de notificación
            Text
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaConFiltros() {
    MyApplicationTheme {
        PantallaConFiltros()
    }
}