package com.gmail.pavlovsv93.emulgithub.ui.home.viewmodel

import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTO
import io.reactivex.rxjava3.core.Observable

interface AccountsViewModelInterface {

	val processState: Observable<Boolean>
	val successesState: Observable<GitHubAccountsDTO>
	val errorState: Observable<Exception>

	fun getAllAccounts()
}