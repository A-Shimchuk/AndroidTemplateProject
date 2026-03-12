package com.example.androidtemplateproject.Repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.androidtemplateproject.Models.AppicationData

internal object Repository {
    fun getApplicationById(id: String): AppicationData? {
        return getApplications().find { it.id == id }
    }
    fun getApplications(): List<AppicationData> {
        return listOf(
            AppicationData(
                id = "phone",
                icon = Icons.Default.MailOutline,
                title = "Телефон",
                subtitle = "Звонки и контакты",
                category = "Social"
            ),
            AppicationData(
                id = "mail",
                icon = Icons.Default.ThumbUp,
                title = "Почта",
                subtitle = "Электронная почта",
                category = "Social"
            ),
            AppicationData(
                id = "messages",
                icon = Icons.Default.Notifications,
                title = "Сообщения",
                subtitle = "SMS и мессенджеры",
                category = "Social"
            ),
            AppicationData(
                id = "video",
                icon = Icons.Default.Settings,
                title = "Видео",
                subtitle = "Видеоплеер",
                category = "Media"
            ),
            AppicationData(
                id = "gallery",
                icon = Icons.Default.DateRange,
                title = "Галерея",
                subtitle = "Фотографии и видео",
                category = "Media"
            ),
            AppicationData(
                id = "music",
                icon = Icons.Default.AccountBox,
                title = "Музыка",
                subtitle = "Музыкальный плеер",
                category = "Media"
            ),
            AppicationData(
                id = "maps",
                icon = Icons.Default.AccountCircle,
                title = "Карты",
                subtitle = "Навигация и карты",
                category = "Navigation"
            ),
            AppicationData(
                id = "places",
                icon = Icons.Default.Place,
                title = "Места",
                subtitle = "Избранные места",
                category = "Navigation"
            ),
            AppicationData(
                id = "cloud",
                icon = Icons.Default.AddCircle,
                title = "Облако",
                subtitle = "Облачное хранилище",
                category = "Utilities"
            ),
            AppicationData(
                id = "calculator",
                icon = Icons.Default.Lock,
                title = "Калькулятор",
                subtitle = "Вычисления",
                category = "Utilities"
            ),
            AppicationData(
                id = "calendar",
                icon = Icons.Default.Build,
                title = "Календарь",
                subtitle = "Расписание событий",
                category = "Productivity"
            ),
            AppicationData(
                id = "notes",
                icon = Icons.Default.Call,
                title = "Заметки",
                subtitle = "Записи и заметки",
                category = "Productivity"
            ),
            AppicationData(
                id = "settings",
                icon = Icons.Default.Check,
                title = "Настройки",
                subtitle = "Системные настройки",
                category = "System"
            ),
            AppicationData(
                id = "security",
                icon = Icons.Default.CheckCircle,
                title = "Безопасность",
                subtitle = "Конфиденциальность",
                category = "System"
            ),
            AppicationData(
                id = "about",
                icon = Icons.Default.Delete,
                title = "О приложении",
                subtitle = "Информация",
                category = "System"
            )
        )
    }
}

