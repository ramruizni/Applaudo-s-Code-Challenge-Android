package com.example.tmdbchallenge.ui.composable

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val focusRequester = remember { FocusRequester() }
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp)
            .focusRequester(focusRequester),
        interactionSource = interactionSource,
        singleLine = true
    ) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }


        TextFieldDefaults.TextFieldDecorationBox(
            value = text,
            innerTextField = it,
            singleLine = true,
            enabled = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            visualTransformation = VisualTransformation.None,
            trailingIcon = { /* ... */ },
            placeholder = {
                Text(
                    text = placeholder,
                    fontSize = 14.sp,
                    style = TextStyle(
                        color = Color(0xFF8C8CA1)
                    )
                )
            },
            interactionSource = interactionSource,
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                top = 0.dp, bottom = 0.dp
            )
        )
    }
}