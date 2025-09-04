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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // <-- agregado para evitar error al usar items(...)
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
import androidx.compose.material3.contentColorFor
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
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jcl.Lab7.ui.theme.MyApplicationTheme
import kotlin.collections.List
import androidx.compose.ui.text.style.TextOverflow



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaConFiltros(
                        modifier = Modifier.padding(innerPadding),
                        generateFakeNotifications()
                    )
                }
            }
        }
    }
}

@Composable
fun PantallaConFiltros(
    modifier: Modifier = Modifier,
    generateFakeNotifications: List<Notification>,
) {
    //variables para valores onClick de los chips
    var informativas by remember {mutableStateOf(false)}
    var capacitaciones by remember {mutableStateOf(false)}

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
            fontWeight = Bold,
            fontSize = 16.sp,
            style = TextStyle(color = Color.Black)
        )
        //Fila para chips de filtro
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
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
        // Lista original (ya viene por parámetro)
        val all = generateFakeNotifications

        // Filtrado según chip activo
        val filtered = when {
            informativas -> all.filter { it.type == NotificationType.GENERAL }
            capacitaciones -> all.filter { it.type == NotificationType.NEW_MEETING }
            else -> all
        }

        //Elevated card para notificaciones
        ElevatedCard (
            modifier = Modifier
            .padding(16.dp)
        )
        {
            ElevatedCard(
                modifier = modifier.padding(16.dp)
            ) {
                LazyColumn {
                    items(filtered, key = { it.id }) { n ->
                        val (icon, bg) = NotificationColors(n.type)
                        Notification(
                            modifier = modifier,
                            icono = icon,
                            titulo = n.title,
                            mensaje = n.body,
                            fechaYHora = n.sendAt,
                            colorDeFondoIcono = bg
                        )
                    }
                }
            }

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
    sizeIcono: Dp = 35.dp,
)
{
    //Fila principal para Icono, mensaje, fecha y hora
    Row(
        modifier = Modifier
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
                tint = contentColorFor(colorDeFondoIcono),
                modifier = modifier.padding(2.dp)
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
                    text = titulo,
                    fontWeight = Bold,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                //Texto de Fecha y hora
                Text(
                    text = fechaYHora,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    textAlign = TextAlign.End
                )
            }
            //texto de mensaje
            Text(
                text = mensaje
            )
        }
    }
}

@Composable
private fun NotificationColors(type: NotificationType): Pair<ImageVector, Color> {
    return when (type) {
        NotificationType.GENERAL     -> Pair(Icons.Filled.Notifications, MaterialTheme.colorScheme.primaryContainer)
        NotificationType.NEW_MEETING -> Pair(Icons.Filled.DateRange,    MaterialTheme.colorScheme.tertiaryContainer)
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaConFiltrosPreview() {
    MyApplicationTheme {
        PantallaConFiltros(generateFakeNotifications = generateFakeNotifications())
    }
}
