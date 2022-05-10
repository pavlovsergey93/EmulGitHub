package com.gmail.pavlovsv93.emulgithub.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pavlovsv93.emulgithub.databinding.FragmentHomeBinding
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import com.gmail.pavlovsv93.emulgithub.ui.BaseViewModel
import com.gmail.pavlovsv93.emulgithub.utils.ViewModelStateStore
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import java.util.*

class HomeFragment : Fragment() {
	interface onClickItemAccount {
		fun onClickedItemAccount(accountGitHub: AccountGitHub)
	}

	private var _binding: FragmentHomeBinding? = null
	private val binding get() = _binding!!
	private val adapter: AccountListAdapter = AccountListAdapter(object : onClickItemAccount {
		override fun onClickedItemAccount(accountGitHub: AccountGitHub) {
			Bundle().apply {
				putParcelable(ARG_ACCOUNT_HOME, accountGitHub)
			}.let {
				parentFragmentManager.setFragmentResult(KEY_ACCOUNT_HOME, it)
			}
		}
	})
	private lateinit var viewModel: AccountsViewModelInterface
	private val repos: RepositoryInterface by inject(named("mock_repos"))
	private val store: ViewModelStateStore<BaseViewModel> by inject(named("home_store"))

	private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

	companion object {
		const val KEY_SAVE_INSTANCE_STATE = "savedInstanceState.AccountsViewModel.HomeFragment"
		const val KEY_ACCOUNT_HOME = "KEY_ACCOUNT_HOME"
		const val ARG_ACCOUNT_HOME = "ARG_ACCOUNT_HOME"
		fun newInstance() = HomeFragment()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		var key : String?
		if (savedInstanceState == null) {
			key = UUID.randomUUID().toString()
			viewModel = AccountsViewModel(repos, key)
			viewModel.getAllAccounts()
		} else {
			key = savedInstanceState.getString(KEY_SAVE_INSTANCE_STATE) as String
			viewModel = store.getViewModel(key) as AccountsViewModelInterface
		}
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
		compositeDisposable.dispose()
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
		viewModel.let {
			compositeDisposable.add(
				viewModel.processState
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe { shouldShow ->
						with(binding.homeProgressBar) {
							visibility = if (shouldShow) {
								View.VISIBLE
							} else {
								View.GONE
							}
						}
					}
			)
		}
		viewModel.let {
			compositeDisposable.add(viewModel.errorState
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe() { exception ->
					Snackbar.make(
						binding.root,
						exception.message.toString(),
						Snackbar.LENGTH_SHORT
					).show()
				}
			)
		}
		viewModel.let {
			compositeDisposable.add(viewModel.successesState
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe() { accountList ->
					adapter.setAccountList(accountList)
				}
			)
		}
	}

	override fun onSaveInstanceState(outState: Bundle) {
		store.putViewModel(viewModel.key, viewModel)
		if (viewModel != null) {
			outState.putString(KEY_SAVE_INSTANCE_STATE, viewModel.key)
		}
		super.onSaveInstanceState(outState)
	}
}