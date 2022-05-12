package com.gmail.pavlovsv93.emulgithub.ui.details.account

import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountRepo

sealed class AppStateDetails{
	data class OnSuccess(val accountList: List<AccountRepo>) : AppStateDetails()
	data class OnError(val throwable: Throwable) : AppStateDetails()
	data class OnLoading(val progress: Boolean) : AppStateDetails()
}
