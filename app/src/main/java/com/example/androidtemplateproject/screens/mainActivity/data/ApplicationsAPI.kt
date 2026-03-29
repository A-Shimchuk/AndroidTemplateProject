package com.example.androidtemplateproject.screens.mainActivity.data

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
import com.example.androidtemplateproject.sharedDomainComponents.ApplicationDTO
import kotlinx.coroutines.delay

class ApplicationsAPI {
    suspend fun getApplications(): List<ApplicationDTO> {
        delay(800)

        return return listOf(
            ApplicationDTO(
                id = "phone",
                image = Icons.Default.MailOutline,
                title = "Телефон",
                subtitle = "Звонки и контакты",
                category = "Social"
            ),
            ApplicationDTO(
                id = "mail",
                image = Icons.Default.ThumbUp,
                title = "Почта",
                subtitle = "Электронная почта",
                category = "Social"
            ),
            ApplicationDTO(
                id = "messages",
                image = Icons.Default.Notifications,
                title = "Сообщения",
                subtitle = "SMS и мессенджеры",
                category = "Social"
            ),
            ApplicationDTO(
                id = "video",
                image = Icons.Default.Settings,
                title = "Видео",
                subtitle = "Видеоплеер",
                category = "Media"
            ),
            ApplicationDTO(
                id = "gallery",
                image = Icons.Default.DateRange,
                title = "Галерея",
                subtitle = "Фотографии и видео",
                category = "Media"
            ),
            ApplicationDTO(
                id = "music",
                image = Icons.Default.AccountBox,
                title = "Музыка",
                subtitle = "Музыкальный плеер",
                category = "Media"
            ),
            ApplicationDTO(
                id = "maps",
                image = Icons.Default.AccountCircle,
                title = "Карты",
                subtitle = "Навигация и карты",
                category = "Navigation"
            ),
            ApplicationDTO(
                id = "places",
                image = Icons.Default.Place,
                title = "Места",
                subtitle = "Избранные места",
                category = "Navigation"
            ),
            ApplicationDTO(
                id = "cloud",
                image = Icons.Default.AddCircle,
                title = "Облако",
                subtitle = "Облачное хранилище",
                category = "Utilities"
            ),
            ApplicationDTO(
                id = "calculator",
                image = Icons.Default.Lock,
                title = "Калькулятор",
                subtitle = "Вычисления",
                category = "Utilities"
            ),
            ApplicationDTO(
                id = "calendar",
                image = Icons.Default.Build,
                title = "Календарь",
                subtitle = "Расписание событий",
                category = "Productivity"
            ),
            ApplicationDTO(
                id = "notes",
                image = Icons.Default.Call,
                title = "Заметки",
                subtitle = "Записи и заметки",
                category = "Productivity"
            ),
            ApplicationDTO(
                id = "settings",
                image = Icons.Default.Check,
                title = "Настройки",
                subtitle = "Системные настройки",
                category = "System"
            ),
            ApplicationDTO(
                id = "security",
                image = Icons.Default.CheckCircle,
                title = "Безопасность",
                subtitle = "Конфиденциальность",
                category = "System"
            ),
            ApplicationDTO(
                id = "about",
                image = Icons.Default.Delete,
                title = "О приложении",
                subtitle = "Информация",
                category = "System"
            )
        )
    }
}