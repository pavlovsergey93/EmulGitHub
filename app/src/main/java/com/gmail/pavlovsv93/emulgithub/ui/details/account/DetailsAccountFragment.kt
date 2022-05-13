package com.gmail.pavlovsv93.emulgithub.ui.details.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.databinding.FragmentDetailsAccountBinding
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import androidx.lifecycle.Observer
import com.gmail.pavlovsv93.emulgithub.data.MockRepos

class DetailsAccountFragment : Fragment() {
	companion object {
		const val KEY_ACCOUNT = "KEY_ACCOUNT"
		fun newInstance(accountGitHub: AccountGitHub) = DetailsAccountFragment().apply {
			arguments = Bundle().apply {
				putParcelable(KEY_ACCOUNT, accountGitHub)
			}
		}
	}

	private var _binding: FragmentDetailsAccountBinding? = null
	private val binding get() = _binding!!
	private val adapter: RepoListAdapter = RepoListAdapter()
	private val viewModel: DetailsAccountViewModel by lazy { DetailsAccountViewModel(MockRepos()) }

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentDetailsAccountBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		if (savedInstanceState == null) {
			arguments?.let {
				it.getParcelable<AccountGitHub>(KEY_ACCOUNT)?.let { account ->
					binding.nameTextView.text = account.login
					Picasso.with(requireContext())
						.load(account.avatar)
						.resize(500, 500)
						.centerCrop()
						.placeholder(R.drawable.ic_launcher_foreground)
						.into(binding.avatarImageView)
					viewModel.getDataAccount(account.login)
				}
			}
		}
		super.onViewCreated(view, savedInstanceState)
		val recyclerView: RecyclerView = binding.listRepoRecyclerView
		recyclerView.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
		recyclerView.adapter = adapter
		viewModel.liveData.observe(viewLifecycleOwner, Observer<AppStateDetails> { state ->
			renderData(state)
		})
	}

	private fun renderData(state: AppStateDetails?) {
		when (state) {
			is AppStateDetails.OnError -> {
				showError(state.throwable)
			}
			is AppStateDetails.OnLoading -> {
				showProgress(state.progress)
			}
			is AppStateDetails.OnSuccess -> {
				adapter.setRepoList(state.accountList)
			}
		}

	}

	private fun showError(throwable: Throwable) {
		Snackbar.make(
			binding.root,
			throwable.message.toString(),
			Snackbar.LENGTH_INDEFINITE
		).show()
	}

	private fun showProgress(shouldShow: Boolean) {
		with(binding.progressBar) {
			visibility = if (shouldShow) {
				View.VISIBLE
			} else {
				View.GONE
			}
		}
	}

}