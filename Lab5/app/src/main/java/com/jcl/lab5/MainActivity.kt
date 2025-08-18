package com.jcl.lab5

import android.R.attr.button
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jcl.lab5.ui.theme.Lab5Theme
import android.net.Uri
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.ElevatedCard


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
    val context = LocalContext.current

    //Columna principal de la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    )
    {
        //Fila celeste
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan),
            horizontalArrangement = Arrangement.SpaceAround
        )
        {
            //Icono azul
            Icon(Icons.Outlined.Refresh, contentDescription = null, tint = Color.Blue)
            //texto de actualización disponible
            Text(text = "Actualización disponible")
            //Texto de Descargar con link a playStore
            TextButton(onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
                )
                context.startActivity(intent)
            }) { Text("Descargar") }
        }

        //Fila con fecha de cumpleaños y botón de terminar jornada
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Domingo", fontWeight = FontWeight.Bold, fontSize = 32.sp)
                //Texto de fecha de cumpleaños
                Text(text = "7 de Septiembre")
            }

            //Botón de terminar jornada
            OutlinedButton(onClick = {  }) {
                Text("Terminar jornada", color = Color.Magenta)
            }
        }

        //Card para información del restaurante
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        {
            Column(
                modifier = Modifier
                .padding(16.dp)
            )
            {
                // Columna para los textos del restaurante
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    //fila para nombre y icono de ubicación
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        //Nombre de restaurante
                        Text(
                            text = "Saúl",
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp
                        )
                        Spacer(Modifier.weight(1f))
                        IconButton(onClick = {
                            val uri = Uri.parse("geo:14.5965,-90.5133?q=14.5965,-90.5133(Saúl)")
                            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                                setPackage("com.google.android.apps.maps")
                            }
                            context.startActivity(intent)
                        }) {
                            Icon(Icons.Outlined.Place, contentDescription = "Direcciones")

                        }
                    }
                    Text(text = "6a. Avenida 15-64, Zona 10")
                    Text(text = "7:00-22:00")
                }

                // Fila inferior para los botones
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            Toast.makeText(context, "Javier Alejandro Chávez León", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Text("Iniciar")
                    }
                    // Botón Detalles
                    TextButton(onClick = {
                        Toast.makeText(context, "Cocina internacional moderna \nQQ", Toast.LENGTH_SHORT).show()
                    }) {
                        Text("Detalles")
                    }
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