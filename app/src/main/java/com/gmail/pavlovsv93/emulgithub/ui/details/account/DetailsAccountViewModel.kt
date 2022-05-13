package com.gmail.pavlovsv93.emulgithub.ui.details.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class DetailsAccountViewModel(
	private val repo: RepositoryInterface,
) : ViewModel(){
	private val _liveData : MutableLiveData<AppStateDetails> = MutableLiveData()
	val liveData: LiveData<AppStateDetails> get() = _liveData
	private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

	fun getDataAccount(login: String) {
		_liveData.postValue(AppStateDetails.OnLoading(true))
		compositeDisposable.add(
			repo.getItemAccount(login)
				.subscribeBy(
					onSuccess = { result ->
						_liveData.postValue(AppStateDetails.OnLoading(false))
						_liveData.postValue(AppStateDetails.OnSuccess(result))
					},
					onError = { error ->
						_liveData.postValue(AppStateDetails.OnLoading(false))
						_liveData.postValue(AppStateDetails.OnError(error))
					})
		)
	}
}