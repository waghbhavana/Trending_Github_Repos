package com.example.trending_github_repos.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trending_github_repos.NetworkResult
import com.example.trending_github_repos.models.Item
import com.example.trending_github_repos.models.RepositoriesApiResponse
import com.example.trending_github_repos.retrofit.RepositoriesApi
import com.example.trending_github_repos.room.RoomDao
import com.example.trending_github_repos.utils.Constants
import org.json.JSONObject
import javax.inject.Inject

class RepoRepository @Inject constructor(private val repositoriesApi: RepositoriesApi,
                                         private val roomDao: RoomDao) {

    private val _repos = MutableLiveData<NetworkResult<RepositoriesApiResponse>>()
    val repos: LiveData<NetworkResult<RepositoriesApiResponse>>
    get() = _repos


    suspend fun getRepos() {


        val result = repositoriesApi.getRepositories(Constants.ITEM_SORT,Constants.ITEM_ORDER,Constants.ITEM_SINCE)
        Log.d("RESULT", "getRepos RepoRepository$result")
        if (result.isSuccessful && result.body() != null) {
            //_repos.postValue(result.body())
            _repos.postValue(NetworkResult.Success(result.body()))
            insertRepoItems(result.body()!!.items)
        } else if (result.errorBody() != null) {
            val errorObj = JSONObject(result.errorBody()!!.charStream().readText())
            _repos.postValue(NetworkResult.Error(errorObj.getString("message")))
        }else{
            _repos.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun getRepoFromDB(): List<Item>{
        return roomDao.getRepoItems()
    }

    suspend fun insertRepoItems(list: List<Item>){
        roomDao.insertRepoItems(list)
    }

}