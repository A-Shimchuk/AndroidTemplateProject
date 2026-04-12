package com.example.androidtemplateproject.screens.detailScreen.domain

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetApplicationDetailsUseCaseTest {

    private lateinit var useCase: GetApplicationDetailsUseCase
    private lateinit var mockRepository: DetailRepository

    @Before
    fun setup() {
        mockRepository = mock()
        useCase = GetApplicationDetailsUseCase(mockRepository)
    }

    @Test
    fun testInvokeReturnsData() = runTest {
        val appDetails = AppDetails(
            id = "id",
            name = "name",
            developer = "developer",
            category = "category",
            ageRating = 18,
            size = 0f,
            iconUrl = "url",
            description = "description",
            isInWishlist = true
        )

        whenever(mockRepository.getApplicationById("id")).thenReturn(appDetails)

        val result = useCase("id")

        assertNotNull(result)
        assertEquals("id", result?.id)
        assertEquals("name", result?.name)
        assertEquals(18, result?.ageRating)
        verify(mockRepository).getApplicationById("id")
    }

    @Test
    fun testInvokeReturnsNull() = runTest {
        whenever(mockRepository.getApplicationById("id")).thenReturn(null)

        val result = useCase("id")

        assertNull(result)
        verify(mockRepository).getApplicationById("id")
    }

    @Test
    fun testToggleWishlist() = runTest {
        useCase.toggleWishlist("id")

        verify(mockRepository).toggleWishlist("id")
    }

    @Test
    fun testObserveAppDetails() = runTest {
        val appDetails = AppDetails(
            id = "id",
            name = "name",
            developer = "developer",
            category = "category",
            ageRating = 6,
            size = 10.0f,
            iconUrl = "url",
            description = "description",
            isInWishlist = false
        )

        whenever(mockRepository.observeAppDetails("id")).thenReturn(flowOf(appDetails))

        val result = useCase.observeAppDetails("id")

        verify(mockRepository).observeAppDetails("id")
    }

    @Test
    fun testInvokePassesAllFields() = runTest {
        val appDetails = AppDetails(
            id = "id",
            name = "name",
            developer = "developer",
            category = "category",
            ageRating = 12,
            size = 0f,
            iconUrl = "url",
            screenshotUrlList = listOf("screen1", "screen2"),
            description = "description",
            isInWishlist = true
        )

        whenever(mockRepository.getApplicationById("id")).thenReturn(appDetails)

        val result = useCase("id")

        assertEquals("id", result?.id)
        assertEquals("name", result?.name)
        assertEquals("developer", result?.developer)
        assertEquals("category", result?.category)
        assertEquals(12, result?.ageRating)
        assertEquals(0f, result?.size ?: 0f, 0.001f)
        assertEquals("url", result?.iconUrl)
        assertEquals("description", result?.description)
        assertEquals(true, result?.isInWishlist)
    }
}
