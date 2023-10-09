package com.example.simplecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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
                    SimpleCalculatorLayout()
                }
            }
        }
    }
}

@Composable
fun SimpleCalculatorLayout() {
    var input1 by remember {
        mutableStateOf("0")
    }
    var input2 by remember {
        mutableStateOf("0")
    }

    val sum = calculateSum(input1, input2)
    Column(
        modifier = Modifier
            .padding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.calculate_sum),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.Start),
        )

        EditNumberField(
            label = R.string.first_number,
            leadingIcon = R.drawable.numbers,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
            value = input1,
            onValueChanged = { input1 = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
        )
    }

    EditNumberField(
        label = R.string.second_number,
        leadingIcon = R.drawable.numbers,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
        ),
        value = input2,
        onValueChanged = { input2 = it },
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth(),
    )

    Text(
        text = stringResource(R.string.calculate_sum, sum),
        style = MaterialTheme.typography.displaySmall,
    )

    Spacer(modifier = Modifier.height(150.dp))
}

/*
 *  This sis a reusable text field
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    @StringRes label: Int,
    @DrawableRes leadingIcon: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        singleLine = true,
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions,
    )
}

private fun calculateSum(num1: String, num2: String): String {
    return num1 + num2
}

@Preview(showBackground = true)
@Composable
fun SimpleCalculatorPreview() {
    SimpleCalculatorTheme {
        SimpleCalculatorLayout()
    }
}
