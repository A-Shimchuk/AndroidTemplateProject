package com.example.androidtemplateproject.screens.mainActivity.repositories

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
import com.example.androidtemplateproject.dto.ApplicationData

internal class AppRepository {
    fun getApplicationById(id: String): ApplicationData? {
        return getApplications().find { it.id == id }
    }
    fun getApplications(): List<ApplicationData> {
        return listOf(
            ApplicationData(
                id = "phone",
                icon = Icons.Default.MailOutline,
                title = "Телефон",
                subtitle = "Звонки и контакты",
                category = "Social"
            ),
            ApplicationData(
                id = "mail",
                icon = Icons.Default.ThumbUp,
                title = "Почта",
                subtitle = "Электронная почта",
                category = "Social"
            ),
            ApplicationData(
                id = "messages",
                icon = Icons.Default.Notifications,
                title = "Сообщения",
                subtitle = "SMS и мессенджеры",
                category = "Social"
            ),
            ApplicationData(
                id = "video",
                icon = Icons.Default.Settings,
                title = "Видео",
                subtitle = "Видеоплеер",
                category = "Media"
            ),
            ApplicationData(
                id = "gallery",
                icon = Icons.Default.DateRange,
                title = "Галерея",
                subtitle = "Фотографии и видео",
                category = "Media"
            ),
            ApplicationData(
                id = "music",
                icon = Icons.Default.AccountBox,
                title = "Музыка",
                subtitle = "Музыкальный плеер",
                category = "Media"
            ),
            ApplicationData(
                id = "maps",
                icon = Icons.Default.AccountCircle,
                title = "Карты",
                subtitle = "Навигация и карты",
                category = "Navigation"
            ),
            ApplicationData(
                id = "places",
                icon = Icons.Default.Place,
                title = "Места",
                subtitle = "Избранные места",
                category = "Navigation"
            ),
            ApplicationData(
                id = "cloud",
                icon = Icons.Default.AddCircle,
                title = "Облако",
                subtitle = "Облачное хранилище",
                category = "Utilities"
            ),
            ApplicationData(
                id = "calculator",
                icon = Icons.Default.Lock,
                title = "Калькулятор",
                subtitle = "Вычисления",
                category = "Utilities"
            ),
            ApplicationData(
                id = "calendar",
                icon = Icons.Default.Build,
                title = "Календарь",
                subtitle = "Расписание событий",
                category = "Productivity"
            ),
            ApplicationData(
                id = "notes",
                icon = Icons.Default.Call,
                title = "Заметки",
                subtitle = "Записи и заметки",
                category = "Productivity"
            ),
            ApplicationData(
                id = "settings",
                icon = Icons.Default.Check,
                title = "Настройки",
                subtitle = "Системные настройки",
                category = "System"
            ),
            ApplicationData(
                id = "security",
                icon = Icons.Default.CheckCircle,
                title = "Безопасность",
                subtitle = "Конфиденциальность",
                category = "System"
            ),
            ApplicationData(
                id = "about",
                icon = Icons.Default.Delete,
                title = "О приложении",
                subtitle = "Информация",
                category = "System"
            )
        )
    }
}

