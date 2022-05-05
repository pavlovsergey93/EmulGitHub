package com.gmail.pavlovsv93.emulgithub.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.ui.details.account.DetailsAccountFragment
import com.gmail.pavlovsv93.emulgithub.ui.home.HomeFragment
import com.gmail.pavlovsv93.emulgithub.ui.home.HomeFragment.Companion.ARG_ACCOUNT_HOME
import com.gmail.pavlovsv93.emulgithub.ui.home.HomeFragment.Companion.KEY_ACCOUNT_HOME

const val KEY_SAVE_ACCOUNT = "savedInstanceState.KEY_SAVE_ACCOUNT"

class EmulGitHubActivity : AppCompatActivity() {
	private var accountShow: AccountGitHub? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_emul_git_hub)

		val fragmentHome: HomeFragment = HomeFragment.newInstance()
		val fragmentDetails: DetailsAccountFragment =
			DetailsAccountFragment.newInstance(accountShow)
		if(savedInstanceState == null){
			showFragment(R.id.main_fragment_container_view, fragmentHome, false)
		}
		if (savedInstanceState != null && savedInstanceState.containsKey(KEY_SAVE_ACCOUNT)) {
			accountShow = savedInstanceState.getParcelable(KEY_SAVE_ACCOUNT)
			if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
				showFragment(R.id.main_fragment_container_view, fragmentHome, false)
				showFragment(R.id.details_fragment_container_view_2, fragmentDetails, false)
				Log.d(
					KEY_SAVE_ACCOUNT,
					"LANDSCAPE Показ Details во втором контейнере:"
				)
			} else {
				showFragment(R.id.main_fragment_container_view, fragmentDetails, true)
				Log.d(
					KEY_SAVE_ACCOUNT,
					"Показ Details во втором контейнере"
				)
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

	private fun showFragment(idView: Int, fragment: Fragment, addToBackStack: Boolean) {
		val fm = supportFragmentManager.beginTransaction()
		fm.replace(idView, fragment)
		if (addToBackStack) {
			fm.addToBackStack(fragment.toString())
		}
		fm.commit()
	}

	private fun showFragmentsDetails(
		accountShow: AccountGitHub
	) {
		if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
			supportFragmentManager.beginTransaction()
				.replace(
					R.id.main_fragment_container_view,
					DetailsAccountFragment.newInstance(accountShow)
				)
				.addToBackStack(accountShow.login)
				.commit()
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