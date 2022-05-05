package com.gmail.pavlovsv93.emulgithub.ui.details.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.app
import com.gmail.pavlovsv93.emulgithub.databinding.FragmentDetailsAccountBinding
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.ui.BaseViewModel
import com.gmail.pavlovsv93.emulgithub.ui.details.account.adapter.RepoListAdapter
import com.gmail.pavlovsv93.emulgithub.ui.details.account.viewmodel.DetailsAccountViewModel
import com.gmail.pavlovsv93.emulgithub.ui.details.account.viewmodel.DetailsAccountViewModelInterface
import com.gmail.pavlovsv93.emulgithub.ui.home.HomeFragment
import com.gmail.pavlovsv93.emulgithub.ui.home.viewmodel.AccountsViewModel
import com.gmail.pavlovsv93.emulgithub.ui.home.viewmodel.AccountsViewModelInterface
import com.gmail.pavlovsv93.emulgithub.utils.ViewModelStateStore
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.*

class DetailsAccountFragment : Fragment() {
	companion object {
		const val KEY_SAVE_INSTANCE_SAVE =
			"savedInstanceState.DetailsAccountViewModel.DetailsAccountFragment"

		const val KEY_ACCOUNT = "KEY_ACCOUNT"
		fun newInstance(accountGitHub: AccountGitHub?) = DetailsAccountFragment().apply {
			arguments = Bundle().apply {
				putParcelable(KEY_ACCOUNT, accountGitHub)
			}
		}
	}

	private var _binding: FragmentDetailsAccountBinding? = null
	private val binding get() = _binding!!
	private val adapter: RepoListAdapter = RepoListAdapter()
	private lateinit var viewModel: DetailsAccountViewModelInterface
	private lateinit var compositeDisposable: CompositeDisposable
	private val store: ViewModelStateStore<BaseViewModel> by lazy { requireActivity().app.viewModelStore }

	override fun onCreate(savedInstanceState: Bundle?) {
		var key: String? = null
		if (savedInstanceState == null) {
			key = UUID.randomUUID().toString()
			viewModel = DetailsAccountViewModel(requireActivity().app.repo, key)
			store.putViewModel(key, viewModel)
			Log.d(KEY_SAVE_INSTANCE_SAVE, "Init ViewModelDetails $key \n ${viewModel.key}")
		} else {
			key = savedInstanceState.getString(HomeFragment.KEY_SAVE_INSTANCE_STATE) as String
			viewModel = store.getViewModel(key) as DetailsAccountViewModelInterface
			Log.d(KEY_SAVE_INSTANCE_SAVE, "Init ViewModelDetails $key \n ${viewModel.key}")
		}
		super.onCreate(savedInstanceState)
	}

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
		compositeDisposable.dispose()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		//initViewModel(savedInstanceState)
		compositeDisposable = CompositeDisposable()
		val recyclerView: RecyclerView = binding.listRepoRecyclerView
		recyclerView.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
		recyclerView.adapter = adapter
		compositeDisposable.add(viewModel.processState
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe() { shouldShow ->
				with(binding.progressBar) {
					visibility = if (shouldShow) {
						View.VISIBLE
					} else {
						View.GONE
					}
				}
			})
		compositeDisposable.add(viewModel.errorState
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe() { exception ->
				Snackbar.make(
					binding.root,
					exception.message.toString(),
					Snackbar.LENGTH_INDEFINITE
				).show()
			})
		compositeDisposable.add(viewModel.successState
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe() { result ->
				adapter.setRepoList(result)
			})
		arguments?.let {
			it.getParcelable<AccountGitHub>(KEY_ACCOUNT)?.let { account ->
				binding.nameTextView.text = account.login
				Picasso.with(requireContext())
					.load(account.avatar)
					.resize(500, 500)
					.centerCrop()
					.placeholder(R.drawable.ic_launcher_foreground)
					.into(binding.avatarImageView)
				viewModel?.getDataAccount(account.login)
			}
		}
	}

	private fun initViewModel(savedInstanceState: Bundle?) {
		val key = savedInstanceState?.getString(KEY_SAVE_INSTANCE_SAVE)
			?: UUID.randomUUID().toString()
		viewModel = (store.getViewModel(key)
			?: DetailsAccountViewModel(requireActivity().app.repo, key)) as DetailsAccountViewModelInterface
		store.putViewModel(key, viewModel)
		Log.d(KEY_SAVE_INSTANCE_SAVE, "Init ViewModelDetails $key \n ${viewModel.key}")
	}

	override fun onSaveInstanceState(outState: Bundle) {
		outState.putString(KEY_SAVE_INSTANCE_SAVE, viewModel.key)
		Log.d(KEY_SAVE_INSTANCE_SAVE, "Сохранение ViewModelDetails ${viewModel.key}")
		super.onSaveInstanceState(outState)
	}
}