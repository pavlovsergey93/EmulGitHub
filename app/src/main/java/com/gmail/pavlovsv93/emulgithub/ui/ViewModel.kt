package com.gmail.pavlovsv93.emulgithub.ui

import com.gmail.pavlovsv93.emulgithub.data.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import com.gmail.pavlovsv93.emulgithub.ui.details.account.DetailsAccountViewModelInterface
import com.gmail.pavlovsv93.emulgithub.ui.home.AccountsViewModelInterface
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class ViewModel(
	private val repo: RepositoryInterface,
) : AccountsViewModelInterface, DetailsAccountViewModelInterface {
	private val _processState = BehaviorSubject.create<Boolean>()
	private val _successState = BehaviorSubject.create<AccountGitHub>()
	private val _errorState = BehaviorSubject.create<Exception>()
	private val _successesState = BehaviorSubject.create<MutableList<AccountGitHub>>()
	override val processState: Observable<Boolean> = _processState
	override val successState: Observable<AccountGitHub> = _successState
	override val errorState: Observable<Exception> = _errorState
	override val successesState: Observable<MutableList<AccountGitHub>> = _successesState

	override fun getDataAccount(uid: String) {
		_processState.onNext(true)
		repo.getItemAccount(uid).subscribe{ result ->
			_processState.onNext(false)
			if (result != null){
				_successState.onNext(result)
			}else{
				_errorState.onNext(throw IllegalArgumentException())
			}
		}
	}

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