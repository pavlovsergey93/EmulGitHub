package com.gmail.pavlovsv93.emulgithub.ui

import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub

sealed class AppState {
	data class OnSuccess(val accountList: List<AccountGitHub>) : AppState()
	data class OnError(val throwable: Throwable) : AppState()
	data class OnLoading(val progress: Boolean) : AppState()
}