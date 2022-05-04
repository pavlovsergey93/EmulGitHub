package com.gmail.pavlovsv93.emulgithub.ui.details.account.viewmodel

import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountRepo
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTO
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTOItem
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import com.gmail.pavlovsv93.emulgithub.ui.BaseViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class DetailsAccountViewModel(
	private val repo: RepositoryInterface,
	override val key: String
) : DetailsAccountViewModelInterface, BaseViewModel {
	private val _processState = BehaviorSubject.create<Boolean>()
	private val _successState = BehaviorSubject.create<List<AccountRepo>>()
	private val _errorState = BehaviorSubject.create<Throwable>()
	override val processState: Observable<Boolean> = _processState
	override val successState: Observable<List<AccountRepo>> = _successState
	override val errorState: Observable<Throwable> = _errorState

	override fun getDataAccount(login: String) {
		_processState.onNext(true)
		repo.getItemAccount(login)
			.map { convertRepos(it) }
			.subscribeBy(
				onSuccess = { result ->
					_processState.onNext(false)
					_successState.onNext(result)
				},
				onError = { error ->
					_processState.onNext(false)
					_errorState.onNext(error)
				})
	}

	private fun convertRepos(reposListDTO: List<GitHubReposDTOItem>): List<AccountRepo> {
		val reposList: MutableList<AccountRepo> = mutableListOf()
		reposListDTO.forEach {
			val accountRepo = AccountRepo(
				idRepos = it.nodeId,
				title = it.name,
				description = it.description,
				urlHtmlRepos = it.htmlUrl
			)
			reposList.add(accountRepo)
		}
		return reposList
	}
}