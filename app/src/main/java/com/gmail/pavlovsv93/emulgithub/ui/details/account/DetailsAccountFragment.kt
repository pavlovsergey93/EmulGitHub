package com.gmail.pavlovsv93.emulgithub.ui.details.account

import android.os.Bundle
import androidx.fragment.app.Fragment

class DetailsAccountFragment : Fragment() {
	companion object {
		const val KEY_ACCOUNT = "KEY_ACCOUNT"
		fun newInstance(accountId: String) = DetailsAccountFragment().apply {
			arguments = Bundle().apply {
				putString(KEY_ACCOUNT, accountId)
			}
		}
	}

}