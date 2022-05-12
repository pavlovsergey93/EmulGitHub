package com.gmail.pavlovsv93.emulgithub.ui

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.ui.details.account.ACCOUNT_EXTRA
import com.gmail.pavlovsv93.emulgithub.ui.details.account.DetailsAccountActivity
import com.gmail.pavlovsv93.emulgithub.ui.details.account.DetailsAccountFragment
import com.gmail.pavlovsv93.emulgithub.ui.home.HomeFragment
import com.gmail.pavlovsv93.emulgithub.ui.home.HomeFragment.Companion.ARG_ACCOUNT_HOME
import com.gmail.pavlovsv93.emulgithub.ui.home.HomeFragment.Companion.KEY_ACCOUNT_HOME

const val KEY_SAVE_ACCOUNT = "savedInstanceState.KEY_SAVE_ACCOUNT"
const val TAG_FRAGMENT_HOME = "TAG_FRAGMENT_HOME"

class EmulGitHubActivity : AppCompatActivity() {
	private var accountShow: AccountGitHub? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_emul_git_hub)
		if (savedInstanceState == null) {
			supportFragmentManager.beginTransaction()
				.replace(
					R.id.main_fragment_container_view,
					HomeFragment.newInstance(),
					TAG_FRAGMENT_HOME
				)
				.commit()
		}
		if (savedInstanceState != null) {
			if (savedInstanceState.containsKey(KEY_SAVE_ACCOUNT)) {
				accountShow =
					savedInstanceState.getParcelable<AccountGitHub>(KEY_SAVE_ACCOUNT) as AccountGitHub
			}
			accountShow?.let {
				if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
					supportFragmentManager.beginTransaction()
						.replace(
							R.id.details_fragment_container_view_2,
							DetailsAccountFragment.newInstance(accountShow!!)
						)
						.commit()
				} else {
					Intent(this, DetailsAccountActivity::class.java)
						.apply {
							putExtra(ACCOUNT_EXTRA, accountShow)
						}.let {
							startActivity(it)
						}
				}
			}
		}

		supportFragmentManager.setFragmentResultListener(
			KEY_ACCOUNT_HOME,
			this,
			FragmentResultListener
			{ _, result ->
				result.getParcelable<AccountGitHub>(ARG_ACCOUNT_HOME)
					?.let { it ->
						accountShow = it
						showFragmentsDetails(it)
					}
			})
	}

	private fun showFragment(idView: Int, fragment: Fragment) {
		supportFragmentManager.beginTransaction()
			.replace(idView, fragment)
			.commit()
	}

	private fun showFragmentsDetails(
		accountShow: AccountGitHub
	) {
		if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
			Intent(this, DetailsAccountActivity::class.java)
				.apply {
					putExtra(ACCOUNT_EXTRA, accountShow)
				}.let {
					startActivity(it)
				}
		} else {
			supportFragmentManager.beginTransaction()
				.replace(
					R.id.details_fragment_container_view_2,
					DetailsAccountFragment.newInstance(accountShow)
				)
				.commit()
		}
	}

	override fun onSaveInstanceState(outState: Bundle) {
		if (accountShow != null) {
			outState.putParcelable(KEY_SAVE_ACCOUNT, accountShow)
		}
		super.onSaveInstanceState(outState)
	}
}