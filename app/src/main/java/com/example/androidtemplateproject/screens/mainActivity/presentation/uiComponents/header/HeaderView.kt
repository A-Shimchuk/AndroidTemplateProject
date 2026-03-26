package com.example.androidtemplateproject.screens.mainActivity.presentation.uiComponents.header

import android.util.Size
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun HeaderView(modifier: Modifier? = null) {
    val rowHeight = 70
    val defaultPadding = 16
    val safeAreaSpacerHeight = 30
    val iconSize = Size(50, 50)

    Spacer(modifier = Modifier.height(safeAreaSpacerHeight.dp))

    Row(
        modifier = modifier ?: Modifier
            .fillMaxWidth()
            .height(rowHeight.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HeaderIcon(
            Modifier
                .padding(start = defaultPadding.dp)
                .size(iconSize.width.dp, iconSize.height.dp)
        )

        HeaderTitle(
            "RuStore", modifier = Modifier
                .weight(1f)
                .padding(start = defaultPadding.dp)
        )

        HeaderIcon(
            Modifier
                .padding(end = defaultPadding.dp)
                .size(iconSize.width.dp, iconSize.height.dp)
        )
    }
}