package org.bff.erp.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.bff.erp.model.Despesas
import org.bff.erp.model.Faturamento
import org.bff.erp.util.DefaultColors.corDespesas
import org.bff.erp.util.DefaultColors.corFaturamento



@Composable
expect fun dashBoardView()


@Composable
fun loadDashBoardView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .padding(start = 150.dp, top = 50.dp)
        ) {
            Row(
                modifier = Modifier.padding(15.dp)
            ) {
                // Gráfico de pizza
                Canvas(
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                ) {
                    val total = 100f
                    val expenses = 25f
                    val revenues = 75f

                    val expensesAngle = (expenses / total) * 360f
                    val revenuesAngle = (revenues / total) * 360f

                    var startAngle = 0f

                    drawArc(
                        color = corDespesas,
                        startAngle = startAngle,
                        sweepAngle = expensesAngle,
                        useCenter = false,
                        style = Stroke(width = 20f)
                    )
                    startAngle += expensesAngle

                    drawArc(
                        color = corFaturamento,
                        startAngle = startAngle,
                        sweepAngle = revenuesAngle,
                        useCenter = false,
                        style = Stroke(width = 20f)
                    )
                }

                Column(
                    modifier = Modifier.padding(start = 30.dp) // Espaço entre o gráfico e os textos
                ) {
                    Text(
                        text = "Despesas: R$25,00",
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(10.dp)) // Espaço entre os textos
                    Text(
                        fontSize = 15.sp,
                        text = "Faturamento: R$75,00",
                        color = Color.Black
                    )
                }
            }
        }
    }
    mostrarMovimentacoes()
}

@Composable
fun mostrarMovimentacoes() {
    val (faturamentos, despesas) = gerarMocks()

    Card(
        modifier = Modifier
            .padding(start = 150.dp, top = 330.dp)
    ) {
        Column(
            modifier = Modifier.padding(
                start = 15.dp,
                top = 10.dp,
                end = 15.dp,
                bottom = 10.dp
            )
                .width(750.dp)
                .fillMaxHeight()
        ) {
            Text(
                fontSize = 15.sp,
                text = "Movimentações",
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(10.dp)) // Espaço entre o título e as movimentações

            // Cria uma LazyColumn para rolagem
            LazyColumn {
                items(faturamentos) { faturamento ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = faturamento.nome,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                        Text(
                            text = faturamento.valor.toString(),
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                }

                items(despesas) { despesa ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = despesa.nome,
                            fontSize = 14.sp,
                            color = Color.Red // Mudando a cor para destacar despesas
                        )
                        Text(
                            text = despesa.valor.toString(),
                            fontSize = 14.sp,
                            color = Color.Red
                        )
                    }
                }
            }
        }
    }
}

fun gerarMocks(): Pair<List<Faturamento>, List<Despesas>> {
    val faturamentos = listOf(
        Faturamento("Tapete Voador", "R$50,00"),
        Faturamento("Pronpulsor da startrek", "R$70,00"),
        Faturamento("Serviço 1", "R$30,00"),
        Faturamento("Venda de Produto 3", "R$100,00"),
        Faturamento("Venda de Produto 4", "R$80,00"),
        Faturamento("Venda de Produto 5", "R$60,00"),
        Faturamento("Venda de Produto 6", "R$90,00"),
        Faturamento("Venda de Produto 7", "R$110,00"),
        Faturamento("Venda de Produto 8", "R$150,00"),
        Faturamento("Serviço 2", "R$40,00")
    )

    val despesas = listOf(
        Despesas("Compra de Material 1", "R$15,00"),
        Despesas("Compra de Material 2", "R$20,00"),
        Despesas("Aluguel", "R$300,00"),
        Despesas("Conta de Luz", "R$100,00"),
        Despesas("Salário", "R$2000,00")
    )

    return Pair(faturamentos, despesas)
}




