package com.gmail.pavlovsv93.emulgithub.di

import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import com.gmail.pavlovsv93.emulgithub.ui.home.AccountsViewModel
import com.gmail.pavlovsv93.emulgithub.ui.home.AccountsViewModelInterface
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.*

val appModuleHome = module {
	single<String>(named("key_home_view_model")) { UUID.randomUUID().toString() }
	viewModel<AccountsViewModel>(named("accounts_view_model")) {
		AccountsViewModel(
			repo = get<RepositoryInterface>(named(REPOS_USED)),
			key = get(named("key_home_view_model"))
		)
	}
}