package com.example.trending_github_repos.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.trending_github_repos.getOrAwaitValue
import com.example.trending_github_repos.repository.RepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get: Rule
    val instanceTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repoRepository: RepoRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_getRepos_expectedError() = runTest {
      //  Mockito.`when`(repoRepository.getRepos()).thenReturn(NetworkResult.Error("something went wrong"))
        val sut = MainViewModel(repoRepository)
        sut.reposLiveData
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.reposLiveData.getOrAwaitValue()
        Assert.assertEquals(0, result.data!!.items.size)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}









