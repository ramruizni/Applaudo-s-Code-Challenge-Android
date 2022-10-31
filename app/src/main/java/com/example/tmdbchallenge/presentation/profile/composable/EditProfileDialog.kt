package com.example.tmdbchallenge.presentation.profile.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.tmdbchallenge.R
import com.example.tmdbchallenge.presentation.login.LoginEvent
import com.example.tmdbchallenge.ui.composable.FormTextField

@Composable
fun EditProfileDialog(
    show: Boolean,
    name: String,
    nameError: String?,
    socialNetwork: String,
    socialNetworkError: String?,
    onNameChange: (String) -> Unit,
    onSocialNetworkChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSubmit: () -> Unit,
) {
    if (show) {
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Card(
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    FormTextField(
                        value = name,
                        onValueChange = onNameChange,
                        errorMsg = nameError,
                        placeholder = stringResource(R.string.edit_profile_name_placeholder),
                        keyboardType = KeyboardType.Text
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    FormTextField(
                        value = socialNetwork,
                        onValueChange = onSocialNetworkChange,
                        errorMsg = socialNetworkError,
                        placeholder = stringResource(R.string.edit_profile_social_network_placeholder),
                        keyboardType = KeyboardType.Text
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Box(modifier = Modifier
                            .padding(12.dp)
                            .clickable {
                                onDismiss()
                            }) {
                            Text(
                                text = stringResource(R.string.edit_profile_dismiss),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 16.sp,
                                    color = Color(0xFFF65164)
                                )
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(modifier = Modifier
                            .padding(12.dp)
                            .clickable {
                                onSubmit()
                            }) {
                            Text(
                                text = stringResource(R.string.edit_profile_confirm),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 16.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}