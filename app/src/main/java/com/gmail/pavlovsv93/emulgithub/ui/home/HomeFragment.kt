package com.gmail.pavlovsv93.emulgithub.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pavlovsv93.emulgithub.databinding.FragmentHomeBinding
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.ui.AppState
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class HomeFragment : Fragment() {

	interface onClickItemAccount {
		fun onClickedItemAccount(accountGitHub: AccountGitHub)
	}
	private var _binding: FragmentHomeBinding? = null
	private val binding get() = _binding!!
	private val viewModel: AccountsViewModel by viewModel(named("accounts_view_model"))
	private val adapter: AccountListAdapter = AccountListAdapter(object : onClickItemAccount {
		override fun onClickedItemAccount(accountGitHub: AccountGitHub) {
			Bundle().apply {
				putParcelable(ARG_ACCOUNT_HOME, accountGitHub)
			}.let {
				parentFragmentManager.setFragmentResult(KEY_ACCOUNT_HOME, it)
			}
		}
	})

	companion object {
		const val KEY_ACCOUNT_HOME = "KEY_ACCOUNT_HOME"
		const val ARG_ACCOUNT_HOME = "ARG_ACCOUNT_HOME"
		fun newInstance() = HomeFragment()
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val recyclerView: RecyclerView = binding.accountsRecyclerView
		recyclerView.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
		recyclerView.adapter = adapter
		recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
			override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
				super.onScrollStateChanged(recyclerView, newState)
				if (newState == 0) {
					var position = recyclerView.layoutManager?.itemCount as Int
					if (recyclerView.layoutManager?.findViewByPosition(--position)?.isVisible == true) {
						viewModel.getAllAccounts(adapter.getAccountList()[position].id)
					}
				}
			}
		})
		viewModel.liveData
			.observe(viewLifecycleOwner, Observer<AppState> { state ->
				renderData(state)
			})
		if (savedInstanceState == null) viewModel.getAllAccounts()
	}

	private fun renderData(state: AppState) {
		when (state) {
			is AppState.OnError -> {
			showError(state.throwable)
			}
			is AppState.OnLoading -> {
				showProgress(state.progress)
			}
			is AppState.OnSuccess -> {
				adapter.setAccountList(state.accountList)
			}
		}
	}

	private fun showProgress(shouldShow: Boolean) {
		with(binding.homeProgressBar) {
			visibility = if (shouldShow) {
				View.VISIBLE
			} else {
				View.GONE
			}
		}
	}

	private fun showError(error: Throwable) {
		Snackbar.make(
			binding.root,
			error.message.toString(),
			Snackbar.LENGTH_SHORT
		).show()
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}
