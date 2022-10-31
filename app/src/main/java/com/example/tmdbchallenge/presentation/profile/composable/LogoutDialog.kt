package com.example.tmdbchallenge.presentation.profile.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.tmdbchallenge.R

@Composable
fun LogoutDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onSubmit: () -> Unit,
) {
    if (show) {
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.wrapContentHeight()
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(top = 24.dp, start = 24.dp, end = 17.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.profile_logout_dialog_text),
                        style = MaterialTheme.typography.bodyLarge
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
                                onSubmit()
                            }) {
                            Text(
                                text = stringResource(R.string.profile_logout_dialog_leave),
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
                                onDismiss()
                            }) {
                            Text(
                                text = stringResource(R.string.profile_logout_dialog_stay),
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