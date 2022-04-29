package com.gmail.pavlovsv93.emulgithub.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.app
import com.gmail.pavlovsv93.emulgithub.databinding.FragmentHomeBinding
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.ui.details.account.DetailsAccountFragment
import com.gmail.pavlovsv93.emulgithub.ui.home.adapter.AccountListAdapter
import com.gmail.pavlovsv93.emulgithub.ui.home.viewmodel.AccountsViewModel
import com.gmail.pavlovsv93.emulgithub.ui.home.viewmodel.AccountsViewModelInterface
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class HomeFragment : Fragment() {
	interface onClickItemAccount {
		fun onClickedItemAccount(accountGitHub: AccountGitHub)
	}

	private var _binding: FragmentHomeBinding? = null
	private val binding get() = _binding!!
	private val adapter: AccountListAdapter = AccountListAdapter(object : onClickItemAccount {
		override fun onClickedItemAccount(accountGitHub: AccountGitHub) {
			requireActivity().supportFragmentManager.beginTransaction()
				.replace(
					R.id.main_fragment_container_view,
					DetailsAccountFragment.newInstance(accountGitHub)
				)
				.addToBackStack(accountGitHub.login)
				.commit()
		}
	})
	private val viewModel: AccountsViewModelInterface by lazy {
		AccountsViewModel(requireActivity().app.repo)
	}
	private lateinit var compositeDisposable: CompositeDisposable

	companion object {
		fun newInstance() = HomeFragment()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		retainInstance = true
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		compositeDisposable = CompositeDisposable()
		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
		compositeDisposable.dispose()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val recyclerView: RecyclerView = binding.accountsRecyclerView
		recyclerView.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
		recyclerView.adapter = adapter
		viewModel.getAllAccounts()
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
						exception.toString(),
						Snackbar.LENGTH_INDEFINITE
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
}