package com.jcl.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jcl.lab4.ui.theme.Lab4Theme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab4Theme {
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

    //Contenedor de pantalla1
    Box(
        modifier = Modifier
        .fillMaxSize()
            .border(
                width = 10.dp,
                color = Color(0xFF078B45), //Verde utilizado en la página de UVG
                shape = RectangleShape
            )
    ){
        //Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.logouvg),
            contentDescription = "Logo de UVG de fondo",
            modifier = Modifier.fillMaxSize()
            .alpha(0.3f),
            contentScale = ContentScale.Fit
        )
    }
    //Columna principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ){
     //Texto de título UVG
        Text(
            text = "Universidad del Valle de Guatemala",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

        )

        Spacer(modifier = Modifier.height(50.dp))
        //Texto de plataformas móviles
        Text(
            text = "Programación de plataformas móviles, sección 30",
            style = TextStyle(
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(50.dp))
        //Fila para integrantes
        Row(
            modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
        )
        {
            Text(
                text = "Integrantes",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

            )
            Text(
                text = "Javier Chávez\nEnya Gálvez\nAxel Cruz",
                style = TextStyle(
                    textAlign = TextAlign.Center
                )

            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        //Fila para catedrático
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        )
        {
            Text(
                text = "Catedrático",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
            Text(
                text = "Juan Carlos Durini"
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Javier Chávez\n23132",
            style = TextStyle(
                textAlign = TextAlign.Center
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun Pantalla1Preview() {
    Lab4Theme {
    Pantalla1()
    }
}