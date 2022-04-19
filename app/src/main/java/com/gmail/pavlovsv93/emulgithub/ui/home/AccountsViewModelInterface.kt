package com.gmail.pavlovsv93.emulgithub.ui.home

import com.gmail.pavlovsv93.emulgithub.data.AccountGitHub
import io.reactivex.rxjava3.core.Observable

interface AccountsViewModelInterface {

	val processState: Observable<Boolean>
	val successesState: Observable<MutableList<AccountGitHub>>
	val errorState: Observable<Exception>

	fun getAllAccounts()
}