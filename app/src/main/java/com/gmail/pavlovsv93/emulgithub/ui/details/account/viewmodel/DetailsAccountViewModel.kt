package com.gmail.pavlovsv93.emulgithub.ui.details.account.viewmodel

import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountRepo
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTO
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTOItem
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class DetailsAccountViewModel(
	private val repo: RepositoryInterface,
) : DetailsAccountViewModelInterface {
	private val _processState = BehaviorSubject.create<Boolean>()
	private val _successState = BehaviorSubject.create<List<AccountRepo>>()
	private val _errorState = BehaviorSubject.create<Exception>()
	override val processState: Observable<Boolean> = _processState
	override val successState: Observable<List<AccountRepo>> = _successState
	override val errorState: Observable<Exception> = _errorState

	override fun getDataAccount(login: String) {
		_processState.onNext(true)
		repo.getItemAccount(login)
			.map { convertRepos(it) }
			.subscribe{ result ->
			_processState.onNext(false)
			if (result != null){
				_successState.onNext(result)
			}else{
				_errorState.onNext(throw IllegalArgumentException())
			}
		}
	}
	private fun convertRepos(reposListDTO : List<GitHubReposDTOItem>): List<AccountRepo>{
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