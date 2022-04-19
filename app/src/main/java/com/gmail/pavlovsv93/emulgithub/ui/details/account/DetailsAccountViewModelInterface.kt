package com.gmail.pavlovsv93.emulgithub.ui.details.account

import com.gmail.pavlovsv93.emulgithub.data.AccountGitHub
import io.reactivex.rxjava3.core.Observable

interface DetailsAccountViewModelInterface {

	val processState: Observable<Boolean>
	val successState: Observable<AccountGitHub>
	val errorState: Observable<Exception>

	fun getDataAccount(uid: String)
}