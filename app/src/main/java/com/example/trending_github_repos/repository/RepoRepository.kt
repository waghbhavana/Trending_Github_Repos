package com.example.trending_github_repos.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trending_github_repos.NetworkResult
import com.example.trending_github_repos.models.Item
import com.example.trending_github_repos.models.RepositoriesApiResponse
import com.example.trending_github_repos.retrofit.RepositoriesApi
import com.example.trending_github_repos.room.RoomDao
import com.example.trending_github_repos.utils.Constants
import com.example.trending_github_repos.utils.NetworkCheck
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONObject
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class RepoRepository @Inject constructor(
    @ApplicationContext private val context: Context, private val repositoriesApi: RepositoriesApi,
    private val roomDao: RoomDao
) {

    private val _repos = MutableLiveData<NetworkResult<RepositoriesApiResponse>>()
    val repos: LiveData<NetworkResult<RepositoriesApiResponse>>
        get() = _repos


    suspend fun getRepos() {
        _repos.postValue(NetworkResult.Loading())
        if (NetworkCheck.isDeviceOnline(context)) {

            val result = repositoriesApi.getRepositories(
                Constants.ITEM_SORT,
                Constants.ITEM_ORDER,
                Constants.ITEM_SINCE
            )
            if (result.isSuccessful && result.body() != null) {
                roomDao.insertRepoItems(result.body()!!.items)
                _repos.postValue(NetworkResult.Success(result.body()))

            } else if (result.errorBody() != null) {
                val errorObj = JSONObject(result.errorBody()!!.charStream().readText())
                _repos.postValue(NetworkResult.Error(errorObj.getString("message")))
            } else {
                _repos.postValue(NetworkResult.Error("Something went wrong"))
            }
        } else {
            getReposFromDB()
        }
    }

    suspend fun getRepoItemsBySearch(searchQuery: String) {
        val list = roomDao.getRepoItemsBySearch(searchQuery)
        val repositoriesApiResponse = RepositoriesApiResponse(list, list.size)
        _repos.postValue(NetworkResult.Success(repositoriesApiResponse))
    }

     suspend fun getReposFromDB() {
        val list = roomDao.getRepoItems()
        if (list.isNotEmpty()) {
            val repositoriesApiResponse = RepositoriesApiResponse(list, list.size)
            _repos.postValue(NetworkResult.Success(repositoriesApiResponse))
        } else {
            _repos.postValue(NetworkResult.Error("Something went wrong"))
        }

    }

    suspend fun getUpdatedRepo(id: Int) {
        roomDao.getUpdatedRepo(id)
        getReposFromDB()
    }

}