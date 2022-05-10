package com.gmail.pavlovsv93.emulgithub.ui.details.account

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.ui.details.account.DetailsAccountFragment

const val ACCOUNT_EXTRA = "ACCOUNT_EXTRA"

class DetailsAccountActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_details_account)
		val account: AccountGitHub =
			intent?.getParcelableExtra<AccountGitHub>(ACCOUNT_EXTRA) as AccountGitHub
		if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
			finish()
		}
		account?.let {
			supportFragmentManager.beginTransaction()
				.replace(
					R.id.details_fragment_container_view,
					DetailsAccountFragment.newInstance(account)
				)
				.commit()
		}

	}
}