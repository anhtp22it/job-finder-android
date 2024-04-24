package com.tpanh.jobfinder.data.container

import com.tpanh.jobfinder.data.service.AccountService

interface AppContainer {

    val accountService: AccountService

}