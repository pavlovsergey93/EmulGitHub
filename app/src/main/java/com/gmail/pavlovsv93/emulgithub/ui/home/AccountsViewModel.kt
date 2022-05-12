package com.gmail.pavlovsv93.emulgithub.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class AccountsViewModel(
	private val repos: RepositoryInterface
) : ViewModel() {
	private val _liveData: MutableLiveData<AppStateAccount> = MutableLiveData()
	val liveData: LiveData<AppStateAccount> get() = _liveData
	private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

	fun getAllAccounts(since: Int = 0) {
		_liveData.postValue(AppStateAccount.OnLoading(true))
		compositeDisposable
			.add(repos
				.getAllAccount(since)
				.subscribeBy(
					onError = { error ->
						_liveData.postValue(AppStateAccount.OnLoading(false))
						_liveData.postValue(AppStateAccount.OnError(error))
					},
					onSuccess = { result ->
						_liveData.postValue(AppStateAccount.OnLoading(false))
						_liveData.postValue(AppStateAccount.OnSuccess(result))
					}
				)
			)
	}

	override fun onCleared() {
		compositeDisposable.clear()
		super.onCleared()
	}
}