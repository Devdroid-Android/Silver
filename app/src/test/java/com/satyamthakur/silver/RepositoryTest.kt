package com.satyamthakur.silver

import com.satyamthakur.silver.data.model.GenericResultDTO
import com.satyamthakur.silver.data.model.MovieDTO
import com.satyamthakur.silver.data.remote.MovieEndpoint
import com.satyamthakur.silver.data.repository.IMovieRepository
import com.satyamthakur.silver.data.repository.MovieRepository
import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.utility.ErrorParser
import com.satyamthakur.silver.utility.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import java.net.UnknownHostException


@RunWith(RobolectricTestRunner::class)
class RepositoryTest {

    @MockK
    lateinit var movieApiService: MovieEndpoint
    @MockK
    lateinit var errorParser: ErrorParser
    private lateinit var movieRepository: IMovieRepository
    private val context = RuntimeEnvironment.getApplication().applicationContext

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        movieRepository = MovieRepository(
            movieApiService = movieApiService,
            errorParser = errorParser
        )
    }

    @Test
    fun `No Connection should return correct error message`() = runTest {
        val throwable = UnknownHostException()
        coEvery { movieApiService.getPopularMovies() } throws throwable
        coEvery { errorParser(throwable) } returns context.getString(R.string.no_internet)
        val result = movieRepository.getPopularMovies().toList()
        coVerify(exactly = 1) { movieApiService.getPopularMovies() }
        coVerify(exactly = 1) { errorParser(throwable) }
        assertEquals(
            "The first emission should be Loading",
            Resource.Loading,
            result.first()
        )
        assertTrue(
            "The second emission should be Error",
            result.last() is Resource.Error
        )
        assertEquals(
            "The second message should contain Correct Error Message",
            context.getString(R.string.no_internet),
            (result.last() as Resource.Error).message
        )
    }

    @Test
    fun `Success network call should return Expected Data`() = runTest {
        val mockResult: GenericResultDTO<List<MovieDTO>> = GenericResultDTO(
            page = 1,
            results = emptyList()
        )
        coEvery { movieApiService.getPopularMovies() } coAnswers { mockResult }
        val result = movieRepository.getPopularMovies().toList()
        coVerify(exactly = 1) { movieApiService.getPopularMovies() }
        assertEquals(
            "The first emission should be Loading",
            Resource.Loading,
            result.first()
        )
        assertTrue(
            "The second emission should be Success",
            result.last() is Resource.Success
        )
        assertEquals(
            "The second message should contain data",
            Resource.Success(data = emptyList<Movie>()).data,
            (result.last() as Resource.Success).data
        )
    }
}
