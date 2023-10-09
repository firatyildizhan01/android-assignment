package com.arabam.android.assigment.domain.use_case

import androidx.paging.PagingData
import com.arabam.android.assigment.domain.model.ResultUI
import com.arabam.android.assigment.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

import org.mockito.stubbing.OngoingStubbing


class CarUseCaseTest {

    @Mock
    private lateinit var repository: CarRepository

    private lateinit var useCase: CarUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = CarUseCase(repository)
    }

    @Test
    fun `invoke should return Flow of PagingData ResultUI`() = runBlockingTest {
        // Arrange
        val categoryId = null
        val minDate = null
        val maxDate = null
        val minYear = 2010
        val maxYear = 2022
        val sort = 1
        val sortDirection = 0
        val skip = 0
        val take = 10
        val mockPagingData: OngoingStubbing<Flow<PagingData<ResultUI>>>? = /* create mock PagingData */

            // Stub the repository's getCar function to return a Flow of PagingData
            `when`(repository.getCar(
                categoryId, minDate, maxDate, minYear, maxYear, sort, sortDirection, skip, take
            )).thenReturn(flowOf())

        // Act
        val resultFlow: Flow<PagingData<ResultUI>> = useCase(
            categoryId, minDate, maxDate, minYear, maxYear, sort, sortDirection, skip, take
        )

        // Assert
        resultFlow.collect { pagingData ->
            Assert.assertEquals(mockPagingData, pagingData)
        }

        // Verify that the repository method was called with the expected parameters
        verify(repository).getCar(
            categoryId, minDate, maxDate, minYear, maxYear, sort, sortDirection, skip, take
        )
    }
}