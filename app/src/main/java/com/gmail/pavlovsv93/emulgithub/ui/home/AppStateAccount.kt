package com.gmail.pavlovsv93.emulgithub.ui.home

import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub

sealed class AppStateAccount {
	data class OnSuccess(val accountList: List<AccountGitHub>) : AppStateAccount()
	data class OnError(val throwable: Throwable) : AppStateAccount()
	data class OnLoading(val progress: Boolean) : AppStateAccount()
}