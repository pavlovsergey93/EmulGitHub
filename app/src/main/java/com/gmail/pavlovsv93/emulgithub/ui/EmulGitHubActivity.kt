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
		if (savedInstanceState == null) {
			Log.d(KEY_SAVE_ACCOUNT, "Показ ListAccount первый запуск")
			showFragment(R.id.main_fragment_container_view, fragmentHome)
		} else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			showFragment(R.id.main_fragment_container_view, fragmentHome)
			Log.d(KEY_SAVE_ACCOUNT, "LANDSCAPEПоказ Details во втором контейнере")
			showFragment(R.id.details_fragment_container_view_2, fragmentDetails)
		} else if(accountShow != null){
			Log.d(KEY_SAVE_ACCOUNT, "PARTRET Показ Details")
			showFragment(R.id.main_fragment_container_view, fragmentDetails)
		}

//		} else {
//			if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
//				showFragment(R.id.main_fragment_container_view, fragment, TAG_HOME_FRAGMENT)
//				showFragment(R.id.details_fragment_container_view_2, DetailsAccountFragment.newInstance(accountShow), null)
//			} else {
//				if (accountShow == null){
//					showFragment(R.id.main_fragment_container_view, fragment, null)
//				} else {
//					showFragment(
//						R.id.main_fragment_container_view,
//						DetailsAccountFragment.newInstance(accountShow),
//						null
//					)
//				}
//			}
//		}

		supportFragmentManager.setFragmentResultListener(
			KEY_ACCOUNT_HOME,
			this,
			FragmentResultListener
			{ _, result ->
				result.getParcelable<AccountGitHub>(ARG_ACCOUNT_HOME)
					?.let { it -> showFragmentsDetails(it) }
			})
	}

	private fun showFragment(idView: Int, fragment: Fragment) {
		supportFragmentManager.beginTransaction()
			.replace(idView, fragment)
			.addToBackStack(fragment.toString())
			.commit()
	}

	private fun showFragmentsDetails(
		accountShow: AccountGitHub
	) {
		this.accountShow = accountShow
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
}