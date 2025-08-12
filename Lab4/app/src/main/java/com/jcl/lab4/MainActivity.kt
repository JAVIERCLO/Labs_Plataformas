package com.jcl.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jcl.lab4.ui.theme.Lab4Theme

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

    //Imagen de fondo
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.logouvg),
            contentDescription = "Logo de UVG de fondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
            //Investigar cómo difuminar
        )
    }
    //Columna principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
     //Texto de título UVG
        Text(
            text = "Universidad del Valle de Guatemala"
            //style = (investigar el tipo de fuente y tamaño)
        )
        //Texto de plataformas móviles
        Text(
            text = "Programación de plataformas móviles, sección 30"
            //style = (investigar tipo de fuente y tamaño)
        )
        //Caja para integrantes
        Box(
            modifier = Modifier
                .fillMaxWidth()

        )
        {
            Text(
                text = "Integrantes"

            )
            Text(
                text = "Javier Chávez\nCristiano Ronaldo\nLionel Messi"

            )
        }
        //Fila para catedrático
        Row(
            modifier = Modifier
        )
        {
            Text(
                text = "Catedrático"
                //style =
            )
            Text(
                text = "Juan Carlos Durini"
                //style =
            )
        }
        Text(
            text = "Javier Chávez\n23132"
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