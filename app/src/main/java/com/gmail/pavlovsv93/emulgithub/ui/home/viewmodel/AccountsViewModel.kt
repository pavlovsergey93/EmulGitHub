package com.gmail.pavlovsv93.emulgithub.ui.home.viewmodel

import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTO
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTOItem
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class AccountsViewModel(
	private val repo: RepositoryInterface,
) : AccountsViewModelInterface {
	private val _processState = BehaviorSubject.create<Boolean>()
	private val _errorState = BehaviorSubject.create<Exception>()
	private val _successesState = BehaviorSubject.create<List<AccountGitHub>>()
	override val processState: Observable<Boolean> = _processState
	override val errorState: Observable<Exception> = _errorState
	override val successesState: Observable<List<AccountGitHub>> = _successesState

	override fun getAllAccounts(){
		_processState.onNext(true)
		repo.getAllAccount()
			.observeOn(Schedulers.io())
			.map { convertAccount(it) }
			.subscribe{result ->
			_processState.onNext(false)
			if (!result.isEmpty()){
				_successesState.onNext(result)
			} else {
				_errorState.onNext(throw IllegalArgumentException())
			}
		}
	}

	private fun convertAccount(accountsDtoList: List<GitHubAccountsDTOItem>): List<AccountGitHub>{
		val accountsList: MutableList<AccountGitHub> = mutableListOf()
		accountsDtoList.forEach {
			val accountGitHub = AccountGitHub(
				id = it.id,
				avatar = it.avatarUrl,
				login = it.login,
				htmlUrl = it.htmlUrl,
				reposListUrl = it.reposUrl
			)
			accountsList.add(accountGitHub)
		}
		return accountsList
	}
}