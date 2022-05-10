package com.gmail.pavlovsv93.emulgithub.ui.details.account

import androidx.lifecycle.ViewModel
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountRepo
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTOItem
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import com.gmail.pavlovsv93.emulgithub.ui.BaseViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject

class DetailsAccountViewModel(
	private val repo: RepositoryInterface,
	override val key: String,
) : ViewModel(), DetailsAccountViewModelInterface, BaseViewModel {
	private val _processState = BehaviorSubject.create<Boolean>()
	private val _successState = BehaviorSubject.create<List<AccountRepo>>()
	private val _errorState = BehaviorSubject.create<Throwable>()
	override val processState: Observable<Boolean> = _processState
	override val successState: Observable<List<AccountRepo>> = _successState
	override val errorState: Observable<Throwable> = _errorState

	override fun getDataAccount(login: String) {
		_processState.onNext(true)
		repo.getItemAccount(login)
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
}