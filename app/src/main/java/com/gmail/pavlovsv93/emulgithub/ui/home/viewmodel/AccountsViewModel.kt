package com.gmail.pavlovsv93.emulgithub.ui.home.viewmodel

import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTO
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTOItem
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import com.gmail.pavlovsv93.emulgithub.ui.BaseViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class AccountsViewModel(
	private val repo: RepositoryInterface,
	override val key: String,
) : AccountsViewModelInterface, BaseViewModel {
	private val _processState = BehaviorSubject.create<Boolean>()
	private val _errorState = BehaviorSubject.create<Throwable>()
	private val _successesState = BehaviorSubject.create<List<AccountGitHub>>()
	override val processState: Observable<Boolean> = _processState
	override val errorState: Observable<Throwable> = _errorState
	override val successesState: Observable<List<AccountGitHub>> = _successesState

	override fun getAllAccounts(since: Int) {
		_processState.onNext(true)
		repo.getAllAccount(since)
			.subscribeBy(
				onError = { error ->
					_processState.onNext(false)
					_errorState.onNext(error)
				},
				onSuccess = { result ->
					_processState.onNext(false)
					_successesState.onNext(result)
				}
			)
	}
}