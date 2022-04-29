package com.gmail.pavlovsv93.emulgithub.ui.home.viewmodel

import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTO
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface AccountsViewModelInterface {

	val processState: Observable<Boolean>
	val successesState: Observable<List<AccountGitHub>>
	val errorState: Observable<Exception>

	fun getAllAccounts()
}