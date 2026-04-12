package com.example.androidtemplateproject.screens.appList.data

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ApplicationMapperTest {

    private lateinit var mapper: ApplicationMapper

    @Before
    fun setup() {
        mapper = ApplicationMapper()
    }

    @Test
    fun testMapper() {
        val dto = NetworkApplicationDTO(
            id = "id",
            iconUrl = "url",
            name = "name",
            description = "description",
            category = "category"
        )

        val result = mapper.toApplicationData(dto)

        assertEquals(dto.id, result.id)
        assertEquals(dto.iconUrl, result.iconUrl)
        assertEquals(dto.name, result.name)
        assertEquals(dto.description, result.description)
        assertEquals(dto.category, result.category)
    }

    @Test
    fun testIdMapping() {
        val dto = NetworkApplicationDTO(
            id = "id",
            iconUrl = "url",
            name = "name",
            description = "description",
            category = "category"
        )

        val result = mapper.toApplicationData(dto)
        assertEquals("id", result.id)
    }

    @Test
    fun testNameMapping() {
        val dto = NetworkApplicationDTO(
            id = "id",
            iconUrl = "url",
            name = "name",
            description = "description",
            category = "category"
        )

        val result = mapper.toApplicationData(dto)
        assertEquals("name", result.name)
    }

    @Test
    fun testDescriptionMapping() {
        val dto = NetworkApplicationDTO(
            id = "id",
            iconUrl = "url",
            name = "name",
            description = "description",
            category = "category"
        )

        val result = mapper.toApplicationData(dto)
        assertEquals("description", result.description)
    }

    @Test
    fun testCategoryMapping() {
        val dto = NetworkApplicationDTO(
            id = "id",
            iconUrl = "url",
            name = "name",
            description = "description",
            category = "category"
        )

        val result = mapper.toApplicationData(dto)
        assertEquals("category", result.category)
    }
}
