package com.gmail.pavlovsv93.emulgithub.ui.details.account

import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountRepo
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTO
import com.gmail.pavlovsv93.emulgithub.ui.BaseViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface DetailsAccountViewModelInterface : BaseViewModel {

	val processState: Observable<Boolean>
	val successState: Observable<List<AccountRepo>>
	val errorState: Observable<Throwable>

	fun getDataAccount(login: String)
}