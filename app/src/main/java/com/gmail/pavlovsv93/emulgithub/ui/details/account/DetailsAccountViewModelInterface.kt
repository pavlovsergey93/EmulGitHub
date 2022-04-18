package com.gmail.pavlovsv93.emulgithub.ui.details.account

import com.gmail.pavlovsv93.emulgithub.data.AccountGitHub

interface DetailsAccountViewModelInterface {
	fun getDataAccount(uid: String): AccountGitHub
}