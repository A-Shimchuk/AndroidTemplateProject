package com.example.androidtemplateproject.presentation.uiComponents.applicationList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androidtemplateproject.models.AppicationData

@Composable
fun ApplicationCard(data: AppicationData, onApplicationClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .height(100.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(Color.White)
            .clickable {
                onApplicationClick(data.id)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(80.dp),
            imageVector = data.icon,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .padding(start = 6.dp)
                .align(Alignment.CenterVertically)
        ) {

            RowText(data.title, style = MaterialTheme.typography.titleMedium)
            TextSpacer(2)

            RowText(data.subtitle, style = MaterialTheme.typography.bodyMedium)
            TextSpacer(2)

            RowText(data.category, style = MaterialTheme.typography.bodySmall)
        }
    }
}