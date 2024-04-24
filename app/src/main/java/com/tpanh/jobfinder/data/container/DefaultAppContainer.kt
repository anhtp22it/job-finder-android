package com.tpanh.jobfinder.data.container

import android.content.Context
import com.tpanh.jobfinder.data.container.AppContainer
import com.tpanh.jobfinder.data.service.AccountService
import com.tpanh.jobfinder.data.service.impl.AccountServiceImpl
import com.tpanh.jobfinder.data.service.module.FirebaseModule

class DefaultAppContainer(private val context: Context) : AppContainer {
    override val accountService: AccountService by lazy {
        AccountServiceImpl(FirebaseModule.auth())
    }
}
