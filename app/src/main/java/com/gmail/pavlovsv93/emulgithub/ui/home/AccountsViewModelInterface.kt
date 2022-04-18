package com.gmail.pavlovsv93.emulgithub.ui.home

import com.gmail.pavlovsv93.emulgithub.data.AccountGitHub

interface AccountsViewModelInterface {
	fun getAllAccounts(): MutableList<AccountGitHub>
}