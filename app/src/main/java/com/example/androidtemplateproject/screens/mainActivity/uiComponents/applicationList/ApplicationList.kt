package com.example.androidtemplateproject.screens.mainActivity.uiComponents.applicationList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androidtemplateproject.dto.ApplicationData

@Composable
internal fun ApplicationsListView(modifier: Modifier? = null, applications: List<ApplicationData>, onApplicationClick: (String) -> Unit) {
    val cornerRadius = 20

    Column(
        modifier = modifier ?: Modifier
            .fillMaxSize()
            .padding()
            .clip(RoundedCornerShape(topStart = cornerRadius.dp, topEnd = cornerRadius.dp))
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = cornerRadius.dp, topEnd = cornerRadius.dp)
                )
        ) {
            itemsIndexed(applications) { index, application ->
                ApplicationCard(
                    data = application,
                    onApplicationClick = onApplicationClick
                )

                if (index < applications.size - 1) {
                    HorizontalDivider(
                        color = Color.LightGray,
                        thickness = 0.5.dp
                    )
                }
            }
        }
    }
}