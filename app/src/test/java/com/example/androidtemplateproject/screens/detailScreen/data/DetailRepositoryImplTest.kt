package com.example.androidtemplateproject.screens.detailScreen.data

import com.example.androidtemplateproject.screens.detailScreen.data.local.AppDetailsDao
import com.example.androidtemplateproject.screens.detailScreen.data.local.AppDetailsEntity
import com.example.androidtemplateproject.screens.detailScreen.data.local.AppDetailsEntityMapper
import com.example.androidtemplateproject.screens.detailScreen.data.network.DetailApplicationsApiService
import com.example.androidtemplateproject.screens.detailScreen.data.network.NetworkApplicationDetailDTO
import com.example.androidtemplateproject.screens.detailScreen.domain.AppDetails
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DetailRepositoryImplTest {

    private lateinit var repository: DetailRepositoryImpl
    private lateinit var mockDao: AppDetailsDao
    private lateinit var mockEntityMapper: AppDetailsEntityMapper
    private lateinit var mockNetworkMapper: DetailApplicationMapper
    private lateinit var mockApiService: DetailApplicationsApiService

    @Before
    fun setup() {
        mockDao = mock()
        mockEntityMapper = mock()
        mockNetworkMapper = mock()
        mockApiService = mock()
        repository = DetailRepositoryImpl(
            mockDao,
            mockEntityMapper,
            mockNetworkMapper,
            mockApiService
        )
    }

    @Test
    fun testGetFromCache() = runTest {
        val entity = AppDetailsEntity(
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
        val appDetails = AppDetails(
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

        whenever(mockDao.getAppDetails("id")).thenReturn(flowOf(entity))
        whenever(mockEntityMapper.toDomain(entity)).thenReturn(appDetails)

        val result = repository.getApplicationById("id")

        assertEquals("id", result?.id)
        assertEquals("name", result?.name)
        assertEquals(true, result?.isInWishlist)
        verify(mockDao).getAppDetails("id")
        verify(mockEntityMapper).toDomain(entity)
    }

    @Test
    fun testGetFromNetwork() = runTest {
        val dto = NetworkApplicationDetailDTO("id", "url", "name", "description", "category")
        val appDetails = AppDetails(
            id = "id",
            name = "name",
            developer = "Unknown",
            category = "category",
            ageRating = 0,
            size = 0f,
            iconUrl = "url",
            description = "description",
            isInWishlist = false
        )
        val entity = AppDetailsEntity(
            id = "id",
            name = "name",
            developer = "Unknown",
            category = "category",
            ageRating = 0,
            size = 0f,
            iconUrl = "url",
            description = "description",
            isInWishlist = false
        )

        whenever(mockDao.getAppDetails("id")).thenReturn(flowOf(null))
        whenever(mockApiService.getApplicationDetails("id")).thenReturn(dto)
        whenever(mockNetworkMapper.toAppDetails(dto)).thenReturn(appDetails)
        whenever(mockEntityMapper.toEntity(appDetails)).thenReturn(entity)

        val result = repository.getApplicationById("id")

        assertEquals("id", result?.id)
        verify(mockApiService).getApplicationDetails("id")
        verify(mockDao).insertAppDetails(entity)
    }

    @Test
    fun testToggleWishlistToTrue() = runTest {
        val entity = AppDetailsEntity(
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

        whenever(mockDao.getAppDetails("id")).thenReturn(flowOf(entity))

        repository.toggleWishlist("id")

        verify(mockDao).updateWishlistStatus("id", true)
    }

    @Test
    fun testToggleWishlistToFalse() = runTest {
        val entity = AppDetailsEntity(
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

        whenever(mockDao.getAppDetails("id")).thenReturn(flowOf(entity))

        repository.toggleWishlist("id")

        verify(mockDao).updateWishlistStatus("id", false)
    }

    @Test
    fun testGetApplicationByIdNotNull() = runTest {
        val entity = AppDetailsEntity(
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

        whenever(mockDao.getAppDetails("id")).thenReturn(flowOf(entity))
        whenever(mockEntityMapper.toDomain(entity)).thenReturn(
            AppDetails(
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
        )

        val result = repository.getApplicationById("id")

        assertNotNull(result)
        assertEquals("id", result?.id)
    }
}
