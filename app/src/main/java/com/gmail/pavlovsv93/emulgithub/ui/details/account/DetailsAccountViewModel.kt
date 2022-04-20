package com.gmail.pavlovsv93.emulgithub.ui.details.account

import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTO
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class DetailsAccountViewModel(
	private val repo: RepositoryInterface,
) : DetailsAccountViewModelInterface {
	private val _processState = BehaviorSubject.create<Boolean>()
	private val _successState = BehaviorSubject.create<GitHubReposDTO>()
	private val _errorState = BehaviorSubject.create<Exception>()
	override val processState: Observable<Boolean> = _processState
	override val successState: Observable<GitHubReposDTO> = _successState
	override val errorState: Observable<Exception> = _errorState

	override fun getDataAccount(login: String) {
		_processState.onNext(true)
		repo.getItemAccount(login).subscribe{ result ->
			_processState.onNext(false)
			if (result != null){
				_successState.onNext(result)
			}else{
				_errorState.onNext(throw IllegalArgumentException())
			}
		}
	}
}