package com.gmail.pavlovsv93.emulgithub.ui

import com.gmail.pavlovsv93.emulgithub.data.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import com.gmail.pavlovsv93.emulgithub.ui.details.account.DetailsAccountViewModelInterface
import com.gmail.pavlovsv93.emulgithub.ui.home.AccountsViewModelInterface

class ViewModel(private val repo: RepositoryInterface) : AccountsViewModelInterface,
	DetailsAccountViewModelInterface {
	override fun getAllAccounts(): MutableList<AccountGitHub> = repo.getAllAccount()

	override fun getDataAccount(uid: String): AccountGitHub = repo.getItemAccount(uid) as AccountGitHub
}