package com.gmail.pavlovsv93.emulgithub.domain

import com.gmail.pavlovsv93.emulgithub.data.AccountGitHub

interface InMemoryRepositoryInterface {
	fun getAllAccount() : MutableList<AccountGitHub>
	fun getItemAccount(uid: String) : AccountGitHub?
	fun addAccount(account: AccountGitHub)
}