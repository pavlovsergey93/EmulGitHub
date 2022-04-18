package com.gmail.pavlovsv93.emulgithub.ui.details.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.emulgithub.data.AccountGitHub

class DetailsAccountFragment : Fragment() {
	companion object {
		const val KEY_ACCOUNT = "KEY_ACCOUNT"
		fun newInstance(account: AccountGitHub) = DetailsAccountFragment().apply {
			arguments = Bundle().apply {
				putParcelable(KEY_ACCOUNT, account)
			}
		}
	}

}