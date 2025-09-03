package com.jcl.Lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
fun PantallaConFiltros(modifier: Modifier = Modifier) {
    //Columna principal
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        //Fila verde con texto de notificaciones y flecha
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.Green),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            //Icono de flecha
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Flecha para regresar"
            )
            //Texto de notificaciones
            Text(
                text = "Notificaciones",
                style = TextStyle(fontSize = 24.sp, color = Color.White)
            )
        }
        //Texto de tipos de notificaciones
        Text(
            text = "Tipos de notificaciones",
            style = TextStyle(fontSize = 12.sp, color = Color.Black)
        )
        //Fila para chips de filtro
        Row(
            modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            //variables para valores onClick de los chips
            var informativas by remember {mutableStateOf(false)}
            var capacitaciones by remember {mutableStateOf(false)}
            //chip de filtro para notificaciones informativas
            FilterChip(
                selected = informativas,
                onClick =
                    {
                        informativas = !informativas
                        capacitaciones = false
                    },
                label = { Text("Informativas") }
            )
            //chip de filtro para notificaciones de capacitaciones
            FilterChip(
                selected = capacitaciones,
                onClick =
                    {
                    capacitaciones = !capacitaciones
                    informativas = false
                      },
                label = {Text("Capacitaciones")}
            )
        }
        //Elevated card para notificaciones
        ElevatedCard (
            modifier = modifier
            .padding(16.dp)
        )
        {
            //notificación 1
            Notification(
                modifier = modifier,
                icono = Icons.Default.Notifications,
                titulo = "Titulo",
                mensaje = "mensaje para probar preview bla bla bla",
                fechaYHora = "2 sep  7:56 pm",
                colorDeFondoIcono = Color.Yellow
            )
            //notificación 2
            Notification(
                icono = Icons.Default.DateRange,
                titulo = "Titulo",
                mensaje = "mensaje para probar preview bla bla bla",
                fechaYHora = "2 sep  7:56 pm",
                colorDeFondoIcono = Color.Cyan
            )
            //notificación 3
            Notification(
                icono = Icons.Default.DateRange,
                titulo = "Titulo",
                mensaje = "mensaje para probar preview bla bla bla",
                fechaYHora = "2 sep  7:56 pm",
                colorDeFondoIcono = Color.Cyan
            )
            //notificación 4
            Notification(
                icono = Icons.Default.Notifications,
                titulo = "Titulo",
                mensaje = "mensaje para probar preview bla bla bla",
                fechaYHora = "2 sep  7:56 pm",
                colorDeFondoIcono = Color.Yellow
            )
        }
    }
}

//Componente para notificación
@Composable
fun Notification(
    //Parametros de una notificación
    modifier: Modifier = Modifier,
    icono: ImageVector,
    titulo: String,
    mensaje: String,
    fechaYHora: String,
    colorDeFondoIcono: Color = MaterialTheme.colorScheme.primary,
    sizeIcono: Dp = 35.dp
    )
{
    //Fila principal para Icono, mensaje, fecha y hora
    Row(
        modifier = modifier
        .padding(5.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        //Icono de notificación
        Surface(
            modifier = modifier.size(sizeIcono),
            shape = CircleShape,
            color = colorDeFondoIcono
        )
        {
            Icon(
                imageVector = icono,
                contentDescription = "Notificación",
                tint = Color.Black,
                modifier = modifier
                .padding(5.dp)
            )
        }
        Spacer(modifier.width(12.dp))
        //Columna para mensaje (Titulo y abajo mensaje)
        Column(

        )
        {
            //Fila para mensaje (Titulo y abajo mensaje) y fecha/hora
            Row(
                modifier = modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                //Texto de título
                Text(
                    text = titulo
                )
                //Texto de Fecha y hora
                Text(
                    text = fechaYHora
                )
            }
            //texto de mensaje
            Text(
                text = mensaje
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaConFiltrosPreview() {
    MyApplicationTheme {
        PantallaConFiltros()
    }
}