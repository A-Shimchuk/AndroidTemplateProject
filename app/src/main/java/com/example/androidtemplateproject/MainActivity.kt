package com.example.androidtemplateproject

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidtemplateproject.MessageReviewActivity.MessageReviewActivity
import com.example.androidtemplateproject.ui.theme.AndroidTemplateProjectTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTemplateProjectTheme {
                val context = LocalContext.current
                // Создание рективного состояния в Compose
                val textFieldState = remember { mutableStateOf("") }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextField(
                            value = textFieldState.value,
                            onValueChange = { textFieldState.value = it },
                            label = { Text("Введите текст") }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            modifier = Modifier.size(height = 60.dp, width = 240.dp),
                            onClick = { routeToTextReviewActivity(context, textFieldState.value) },
                        ) {
                            Text("Открыть вторую Activity")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            modifier = Modifier.size(height = 60.dp, width = 240.dp),
                            onClick = { call(context, textFieldState.value) }
                        ) {
                            Text("Позвонить другу")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            modifier = Modifier.size(height = 60.dp, width = 240.dp),
                            onClick = { shareText(context, textFieldState.value) }
                        ) {
                            Text("Поделиться текстом")
                        }
                    }
                }
            }
        }
    }

    // MARK: - Private

    private fun routeToTextReviewActivity(context: Context, text: String) {
        val intent = Intent(context, MessageReviewActivity::class.java).apply {
            putExtra("text", text)
        }

        context.startActivity(intent)
    }

    private fun call(context: Context, phone: String) {
        // TODO: - Здесь валидацию на номер можно
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phone")
        }

        context.startActivity(intent)
    }

    private fun shareText(context: Context, text: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }

        context.startActivity(intent)
    }
}