package com.jcl.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jcl.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContadorAvanzado(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ContadorAvanzado(modifier: Modifier = Modifier) {
    var contador by rememberSaveable { mutableStateOf(0) }
    var incremento by rememberSaveable { mutableStateOf(0) }
    var decremento by rememberSaveable { mutableStateOf(0) }
    var maximo by rememberSaveable { mutableStateOf(0) }
    var minimo by rememberSaveable { mutableStateOf(0) }
    val historial = remember { mutableStateListOf<items>() }

    fun ManejoDeContadores(delta: Int) {
        val HayIncremento = delta > 0
        contador += delta
        if (HayIncremento) incremento++ else decremento++

        if (historial.isEmpty()) {
            maximo = contador
            minimo = contador
        } else {
            if (contador > maximo) maximo = contador
            if (contador < minimo) minimo = contador
        }
        historial += items(value = contador, increment = HayIncremento)
    }

    fun reiniciar() {
        contador = 0
        incremento = 0
        decremento = 0
        maximo = 0
        minimo = 0
        historial.clear()
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Título
        Text(
            text = "Javier Chávez",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        //Contador
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            //Borón de decremento
            FilledTonalButton(
                onClick = { ManejoDeContadores(-1) },
                shape = RoundedCornerShape(20.dp)
            ) { Text("−", fontSize = 24.sp) }

            Text(
                text = contador.toString(),
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(min = 80.dp)
            )

            FilledTonalIconButton(
                onClick = {
                    ManejoDeContadores(+1) }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Incrementar")
            }
        }

        Spacer(Modifier.height(16.dp))

        //Datos
        StatsRow(label = "Total incrementos:", value = incremento)
        StatsRow(label = "Total decrementos:", value = decremento)
        StatsRow(label = "Valor máximo:", value = maximo)
        StatsRow(label = "Valor mínimo:", value = minimo)
        StatsRow(label = "Total cambios:", value = incremento + decremento)

        Spacer(Modifier.height(8.dp))

        //Historial
        Text(
            text = "Historial:",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        ) {
            items(historial) { item ->
                Cuadros(item)
            }
        }

        //Reiniciar
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = { reiniciar() },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(28.dp)
        ) {
            Icon(
                Icons.Default.Clear,
                contentDescription = null
            )
            Spacer(Modifier.width(8.dp))
            Text("Reiniciar")
        }
    }
}

@Composable
private fun StatsRow(label: String, value: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 18.sp
        )
        Text(
            text = value.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

private data class items(val value: Int, val increment: Boolean)

@Composable
private fun Cuadros(item: items) {
    val bg = if (item.increment) Color(0xFF1B6E1B) else Color(0xFFB3261E)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(44.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(bg)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = item.value.toString(), color = Color.White, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ContadorAvanzadoPreview() {
    MyApplicationTheme {
        ContadorAvanzado(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}
