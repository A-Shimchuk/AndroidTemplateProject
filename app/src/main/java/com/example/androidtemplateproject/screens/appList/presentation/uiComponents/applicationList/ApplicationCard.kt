package com.example.androidtemplateproject.screens.appList.presentation.uiComponents.applicationList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.androidtemplateproject.sharedUIComponents.RowText
import com.example.androidtemplateproject.sharedUIComponents.TextSpacer
import com.example.androidtemplateproject.screens.appList.domain.ApplicationData

@Composable
internal fun ApplicationCard(data: ApplicationData, onIconClick: (String) -> Unit, onCardClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .height(100.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(Color.White)
            .clickable {
                onCardClick(data.id)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(80.dp)
                .clickable {
                    onIconClick(data.id)
                },
            model = data.iconUrl,
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .padding(start = 6.dp)
                .align(Alignment.CenterVertically)
        ) {

            RowText(data.name, style = MaterialTheme.typography.titleMedium)
            TextSpacer(2)

            RowText(data.description, style = MaterialTheme.typography.bodyMedium)
            TextSpacer(2)

            RowText(data.category, style = MaterialTheme.typography.bodySmall)
        }
    }
}