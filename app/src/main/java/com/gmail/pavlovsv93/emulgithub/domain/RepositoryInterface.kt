package com.gmail.pavlovsv93.emulgithub.domain

import com.gmail.pavlovsv93.emulgithub.data.AccountGitHub
import io.reactivex.rxjava3.core.Observable


interface RepositoryInterface {
	fun getAllAccount() : Observable<MutableList<AccountGitHub>>
	fun getItemAccount(uid: String) : Observable<AccountGitHub>
	fun addAccount(account: AccountGitHub) : Observable<Boolean>
}