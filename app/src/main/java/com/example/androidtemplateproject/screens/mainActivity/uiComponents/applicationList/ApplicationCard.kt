package com.example.androidtemplateproject.screens.mainActivity.uiComponents.applicationList

import android.util.Size
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
import com.example.androidtemplateproject.baseUIComponents.RowText
import com.example.androidtemplateproject.baseUIComponents.TextSpacer
import com.example.androidtemplateproject.dto.ApplicationData

@Composable
internal fun ApplicationCard(data: ApplicationData, onApplicationClick: (String) -> Unit) {
    val appCardImageSize = Size(80, 80)
    val labelsSpacerHeight = 2
    val textColumnStartPadding = 6
    val cornerRadius = 20

    Row(
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .height(100.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = cornerRadius.dp, topEnd = cornerRadius.dp))
            .background(Color.White)
            .clickable {
                onApplicationClick(data.id)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(appCardImageSize.width.dp, appCardImageSize.height.dp),
            imageVector = data.icon,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .padding(start = textColumnStartPadding.dp)
                .align(Alignment.CenterVertically)
        ) {

            RowText(data.title, style = MaterialTheme.typography.titleMedium)
            TextSpacer(labelsSpacerHeight)

            RowText(data.subtitle, style = MaterialTheme.typography.bodyMedium)
            TextSpacer(labelsSpacerHeight)

            RowText(data.category, style = MaterialTheme.typography.bodySmall)
        }
    }
}