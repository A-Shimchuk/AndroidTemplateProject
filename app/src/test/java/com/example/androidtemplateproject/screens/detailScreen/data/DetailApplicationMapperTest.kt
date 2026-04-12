package com.example.androidtemplateproject.screens.detailScreen.data

import com.example.androidtemplateproject.screens.detailScreen.data.network.NetworkApplicationDetailDTO
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class DetailApplicationMapperTest {

    private lateinit var mapper: DetailApplicationMapper

    @Before
    fun setup() {
        mapper = DetailApplicationMapper()
    }

    @Test
    fun testMapper() {
        val dto = NetworkApplicationDetailDTO(
            id = "id",
            iconUrl = "url",
            name = "name",
            description = "description",
            category = "category"
        )

        val result = mapper.toAppDetails(dto)

        assertEquals(dto.id, result.id)
        assertEquals(dto.iconUrl, result.iconUrl)
        assertEquals(dto.name, result.name)
        assertEquals(dto.description, result.description)
        assertEquals(dto.category, result.category)
    }

    @Test
    fun testDefaultValues() {
        val dto = NetworkApplicationDetailDTO(
            id = "id",
            iconUrl = "url",
            name = "name",
            description = "description",
            category = "category"
        )

        val result = mapper.toAppDetails(dto)

        assertEquals("Unknown", result.developer)
        assertEquals(0, result.ageRating)
        assertEquals(0f, result.size, 0.001f)
        assertFalse(result.isInWishlist)
        assertEquals(null, result.screenshotUrlList)
    }

    @Test
    fun testIdField() {
        val dto = NetworkApplicationDetailDTO(
            id = "id",
            iconUrl = "url",
            name = "name",
            description = "description",
            category = "category"
        )

        val result = mapper.toAppDetails(dto)
        assertEquals("id", result.id)
    }

    @Test
    fun testNameField() {
        val dto = NetworkApplicationDetailDTO(
            id = "id",
            iconUrl = "url",
            name = "name",
            description = "description",
            category = "category"
        )

        val result = mapper.toAppDetails(dto)
        assertEquals("name", result.name)
    }

    @Test
    fun testDescriptionField() {
        val dto = NetworkApplicationDetailDTO(
            id = "id",
            iconUrl = "url",
            name = "name",
            description = "description",
            category = "category"
        )

        val result = mapper.toAppDetails(dto)
        assertEquals("description", result.description)
    }
}
