package com.example.androidtemplateproject.screens.detailScreen.data.local

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AppDetailsEntityMapperTest {

    private lateinit var mapper: AppDetailsEntityMapper

    @Before
    fun setup() {
        mapper = AppDetailsEntityMapper()
    }

    @Test
    fun testToEntity() {
        val domain = com.example.androidtemplateproject.screens.detailScreen.domain.AppDetails(
            id = "id",
            name = "name",
            developer = "developer",
            category = "category",
            ageRating = 12,
            size = 0f,
            iconUrl = "url",
            description = "description",
            isInWishlist = true
        )

        val result = mapper.toEntity(domain)

        assertEquals("id", result.id)
        assertEquals("name", result.name)
        assertEquals("developer", result.developer)
        assertEquals("category", result.category)
        assertEquals(12, result.ageRating)
        assertEquals(0f, result.size, 0.001f)
        assertEquals("url", result.iconUrl)
        assertEquals("description", result.description)
        assertTrue(result.isInWishlist)
    }

    @Test
    fun testToDomain() {
        val entity = AppDetailsEntity(
            id = "id",
            name = "name",
            developer = "developer",
            category = "category",
            ageRating = 18,
            size = 0f,
            iconUrl = "url",
            description = "description",
            isInWishlist = false
        )

        val result = mapper.toDomain(entity)

        assertEquals("id", result.id)
        assertEquals("name", result.name)
        assertEquals("developer", result.developer)
        assertEquals("category", result.category)
        assertEquals(18, result.ageRating)
        assertEquals(0f, result.size, 0.001f)
        assertEquals("url", result.iconUrl)
        assertEquals("description", result.description)
        assertFalse(result.isInWishlist)
    }

    @Test
    fun testScreenshotsAreNull() {
        val domain = com.example.androidtemplateproject.screens.detailScreen.domain.AppDetails(
            id = "id",
            name = "name",
            developer = "developer",
            category = "category",
            ageRating = 0,
            size = 0f,
            iconUrl = "url",
            description = "description"
        )

        val result = mapper.toEntity(domain)
        assertNull(result.screenshots)

        val result2 = mapper.toDomain(result)
        assertNull(result2.screenshotUrlList)
    }

    @Test
    fun testWishlistTrue() {
        val domain = com.example.androidtemplateproject.screens.detailScreen.domain.AppDetails(
            id = "id",
            name = "name",
            developer = "developer",
            category = "category",
            ageRating = 0,
            size = 0f,
            iconUrl = "url",
            description = "description",
            isInWishlist = true
        )

        val result = mapper.toEntity(domain)
        assertTrue(result.isInWishlist)
    }

    @Test
    fun testWishlistFalse() {
        val domain = com.example.androidtemplateproject.screens.detailScreen.domain.AppDetails(
            id = "id",
            name = "name",
            developer = "developer",
            category = "category",
            ageRating = 0,
            size = 0f,
            iconUrl = "url",
            description = "description",
            isInWishlist = false
        )

        val result = mapper.toEntity(domain)
        assertFalse(result.isInWishlist)
    }
}
