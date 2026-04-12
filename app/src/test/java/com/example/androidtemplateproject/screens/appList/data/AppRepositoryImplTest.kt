package com.example.androidtemplateproject.screens.appList.data

import com.example.androidtemplateproject.screens.appList.domain.ApplicationData
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AppRepositoryImplTest {

    private lateinit var repository: AppRepositoryImpl
    private lateinit var mockMapper: ApplicationMapper
    private lateinit var mockApiService: ApplicationsApiService

    @Before
    fun setup() {
        mockMapper = mock()
        mockApiService = mock()
        repository = AppRepositoryImpl(mockMapper, mockApiService)
    }

    @Test
    fun testGetApplicationsReturnsEmptyList() = runTest {
        whenever(mockApiService.getApplications()).thenReturn(emptyList())

        val result = repository.getApplications()

        assertTrue(result.isEmpty())
        verify(mockApiService).getApplications()
    }

    @Test
    fun testGetApplicationsReturnsOneItem() = runTest {
        val dto = NetworkApplicationDTO("id", "url", "name", "description", "category")
        val domainData = ApplicationData("id", "url", "name", "description", "category")

        whenever(mockApiService.getApplications()).thenReturn(listOf(dto))
        whenever(mockMapper.toApplicationData(dto)).thenReturn(domainData)

        val result = repository.getApplications()

        assertEquals(1, result.size)
        assertEquals("id", result[0].id)
        verify(mockApiService).getApplications()
        verify(mockMapper).toApplicationData(dto)
    }

    @Test
    fun testGetApplicationsReturnsMultipleItems() = runTest {
        val dto1 = NetworkApplicationDTO("id1", "url1", "name1", "description1", "category1")
        val dto2 = NetworkApplicationDTO("id2", "url2", "name2", "description2", "category2")
        val domain1 = ApplicationData("id1", "url1", "name1", "description1", "category1")
        val domain2 = ApplicationData("id2", "url2", "name2", "description2", "category2")

        whenever(mockApiService.getApplications()).thenReturn(listOf(dto1, dto2))
        whenever(mockMapper.toApplicationData(dto1)).thenReturn(domain1)
        whenever(mockMapper.toApplicationData(dto2)).thenReturn(domain2)

        val result = repository.getApplications()

        assertEquals(2, result.size)
        assertEquals("id1", result[0].id)
        assertEquals("id2", result[1].id)
    }

    @Test
    fun testMapperCalledForEachItem() = runTest {
        val dto1 = NetworkApplicationDTO("id1", "url1", "name1", "description1", "category1")
        val dto2 = NetworkApplicationDTO("id2", "url2", "name2", "description2", "category2")
        val dto3 = NetworkApplicationDTO("id3", "url3", "name3", "description3", "category3")

        whenever(mockApiService.getApplications()).thenReturn(listOf(dto1, dto2, dto3))
        whenever(mockMapper.toApplicationData(dto1)).thenReturn(
            ApplicationData("id1", "url1", "name1", "description1", "category1")
        )
        whenever(mockMapper.toApplicationData(dto2)).thenReturn(
            ApplicationData("id2", "url2", "name2", "description2", "category2")
        )
        whenever(mockMapper.toApplicationData(dto3)).thenReturn(
            ApplicationData("id3", "url3", "name3", "description3", "category3")
        )

        repository.getApplications()

        verify(mockMapper).toApplicationData(dto1)
        verify(mockMapper).toApplicationData(dto2)
        verify(mockMapper).toApplicationData(dto3)
    }

    @Test
    fun testOrderIsPreserved() = runTest {
        val dtos = listOf(
            NetworkApplicationDTO("id1", "url1", "name1", "description1", "category1"),
            NetworkApplicationDTO("id2", "url2", "name2", "description2", "category2"),
            NetworkApplicationDTO("id3", "url3", "name3", "description3", "category3")
        )

        whenever(mockApiService.getApplications()).thenReturn(dtos)
        dtos.forEach { dto ->
            whenever(mockMapper.toApplicationData(dto)).thenReturn(
                ApplicationData(dto.id, dto.iconUrl, dto.name, dto.description, dto.category)
            )
        }

        val result = repository.getApplications()

        assertEquals("id1", result[0].id)
        assertEquals("id2", result[1].id)
        assertEquals("id3", result[2].id)
    }
}
