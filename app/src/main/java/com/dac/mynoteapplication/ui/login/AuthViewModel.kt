package com.dac.mynoteapplication.ui.login

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dac.mynoteapplication.models.UserRequest
import com.dac.mynoteapplication.models.UserResponse
import com.dac.mynoteapplication.repository.UserRepository
import com.dac.mynoteapplication.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository):ViewModel() {

    val userResponseLiveData : LiveData<NetworkResult<UserResponse>>
    get() = userRepository.userResponseLiveData

    fun registerUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepository.registerUser(userRequest)//register4444
        }
    }

    fun loginUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepository.loginUser(userRequest)
        }
    }
    fun validateCredentials(userName: String, emailAddress: String, password: String, isLogin : Boolean) : Pair<Boolean, String> {

        var result = Pair(true, "")
        if(!isLogin && TextUtils.isEmpty(userName) ||TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(password)){
            result = Pair(false, "Please provide the credentials")
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            result = Pair(false, "Please provide valid email")
        }
        else if(!TextUtils.isEmpty(password) && password.length <= 5){
            result = Pair(false, "Password length should be greater than 5")
        }
        return result
    }
}