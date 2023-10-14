package com.example.simplecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.simplecalculator.ui.theme.SimpleCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val viewModel: MainViewModel by viewModels()
                    Column(
                        modifier = Modifier.padding(8.dp).verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = stringResource(R.string.calculate_sum),
                            modifier = Modifier.padding(bottom = 16.dp)
                                .align(alignment = Alignment.Start),
                        )

                        EditNumberField(
                            label = R.string.first_number,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next,
                            ),
                            value = viewModel.input1.value,
                            onValueChanged = { viewModel.setInput1(it) },
                            modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth(),
                        )

                        EditNumberField(
                            label = R.string.second_number,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done,
                            ),
                            value = viewModel.input2.value,
                            onValueChanged = { viewModel.setInput2(it) },
                            modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth(),
                        )

                        Button(
                            onClick = {
                                viewModel.calculateSum(
                                    num1 = viewModel.input1.value.toInt(),
                                    num2 = viewModel.input2.value.toInt(),
                                )
                            },
                        ) {
                            Text(text = "Add")
                        }
                        Text(
                            text = stringResource(R.string.calculate_sum, viewModel.sum),
                            style = MaterialTheme.typography.displaySmall,
                        )
                        Spacer(modifier = Modifier.height(150.dp))
                    }
                }
            }
        }
    }
}

/*
 *  This is a reusable text field
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    @StringRes label: Int,
//    @DrawableRes leadingIcon: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        singleLine = true,
//        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions,
    )
}
