package com.gmail.pavlovsv93.emulgithub.ui.details.account.viewmodel

import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountRepo
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTO
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface DetailsAccountViewModelInterface {

	val processState: Observable<Boolean>
	val successState: Observable<List<AccountRepo>>
	val errorState: Observable<Exception>

	fun getDataAccount(login: String)
}