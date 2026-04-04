package com.example.androidtemplateproject.screens.appList.presentation.uiComponents.applicationList

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
import com.example.androidtemplateproject.screens.appList.domain.ApplicationData

@Composable
internal fun ApplicationsListView(
    modifier: Modifier = Modifier,
    applications: List<ApplicationData>,
    onIconClick: (String) -> Unit,
    onCardClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
        ) {
            itemsIndexed(applications) { index, application ->
                ApplicationCard(
                    data = application,
                    onIconClick = onIconClick,
                    onCardClick = onCardClick
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