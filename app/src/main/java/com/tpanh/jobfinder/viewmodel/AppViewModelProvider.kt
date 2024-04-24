package com.tpanh.jobfinder.viewmodel


import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tpanh.jobfinder.ShopApplication

object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            val application = (this[APPLICATION_KEY] as ShopApplication)
            SignUpViewModel(
                accountService = application.container.accountService
            )
        }
        initializer {
            val application = (this[APPLICATION_KEY] as ShopApplication)
            LoginViewModel(
                accountService = application.container.accountService
            )
        }
    }
}

fun CreationExtras.shopApplication(): ShopApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ShopApplication)