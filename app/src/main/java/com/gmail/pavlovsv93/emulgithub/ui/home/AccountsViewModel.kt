package com.gmail.pavlovsv93.emulgithub.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import com.gmail.pavlovsv93.emulgithub.ui.AppState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject

class AccountsViewModel(
	private val repos: RepositoryInterface
) : ViewModel() {
	private val _liveData: MutableLiveData<AppState> = MutableLiveData()
	val liveData: LiveData<AppState> get() = _liveData
	private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

	fun getAllAccounts(since: Int = 0) {
		_liveData.postValue(AppState.OnLoading(true))
		compositeDisposable
			.add(repos
				.getAllAccount(since)
				.subscribeBy(
					onError = { error ->
						_liveData.postValue(AppState.OnLoading(false))
						_liveData.postValue(AppState.OnError(error))
					},
					onSuccess = { result ->
						_liveData.postValue(AppState.OnLoading(false))
						_liveData.postValue(AppState.OnSuccess(result))
					}
				)
			)
	}

	override fun onCleared() {
		compositeDisposable.clear()
		super.onCleared()
	}
}