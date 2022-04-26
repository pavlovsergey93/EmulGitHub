package com.gmail.pavlovsv93.emulgithub.ui.home.viewmodel

import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTO
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class AccountsViewModel(
	private val repo: RepositoryInterface,
) : AccountsViewModelInterface {
	private val _processState = BehaviorSubject.create<Boolean>()
	private val _errorState = BehaviorSubject.create<Exception>()
	private val _successesState = BehaviorSubject.create<GitHubAccountsDTO>()
	override val processState: Observable<Boolean> = _processState
	override val errorState: Observable<Exception> = _errorState
	override val successesState: Observable<GitHubAccountsDTO> = _successesState

	override fun getAllAccounts(){
		_processState.onNext(true)
		repo.getAllAccount().subscribe{result ->
			_processState.onNext(false)
			if (!result.isEmpty()){
				_successesState.onNext(result)
			} else {
				_errorState.onNext(throw IllegalArgumentException())
			}
		}
	}
}