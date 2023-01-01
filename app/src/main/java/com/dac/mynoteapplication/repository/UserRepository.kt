package com.dac.mynoteapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dac.mynoteapplication.api.UserAPI
import com.dac.mynoteapplication.models.UserRequest
import com.dac.mynoteapplication.models.UserResponse
import com.dac.mynoteapplication.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPI: UserAPI) {

    private val _userResponseLiveData = MutableLiveData<NetworkResult<UserResponse>>()
    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = _userResponseLiveData

    suspend fun registerUser(userRequest: UserRequest) {
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = userAPI.signup(userRequest)////////////register5555
        //Log.d("MyResSignUp",response.body().toString())//register6666
        handelResponse(response)
    }

    suspend fun loginUser(userRequest: UserRequest) {
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = userAPI.signin(userRequest)
        //Log.d("MyResSignin",response.body().toString())
        handelResponse(response)
    }

    private fun handelResponse(response: Response<UserResponse>) {
        Log.d("MyRes", "$response")
        if (response.isSuccessful && response.body() != null) {
            _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))

        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _userResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }
}