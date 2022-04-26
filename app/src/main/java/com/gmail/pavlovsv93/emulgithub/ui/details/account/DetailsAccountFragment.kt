package com.gmail.pavlovsv93.emulgithub.ui.details.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.app
import com.gmail.pavlovsv93.emulgithub.databinding.FragmentDetailsAccountBinding
import com.gmail.pavlovsv93.emulgithub.ui.details.account.adapter.RepoListAdapter
import com.gmail.pavlovsv93.emulgithub.ui.details.account.viewmodel.DetailsAccountViewModel
import com.gmail.pavlovsv93.emulgithub.ui.details.account.viewmodel.DetailsAccountViewModelInterface
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class DetailsAccountFragment : Fragment() {
	companion object {
		const val KEY_ACCOUNT = "KEY_ACCOUNT"
		fun newInstance(accountId: String) = DetailsAccountFragment().apply {
			arguments = Bundle().apply {
				putString(KEY_ACCOUNT, accountId)
			}
		}
	}

	private var _binding: FragmentDetailsAccountBinding? = null
	private val binding get() = _binding!!
	private val adapter: RepoListAdapter = RepoListAdapter()
	private val viewModel: DetailsAccountViewModelInterface by lazy {
		DetailsAccountViewModel(requireActivity().app.repo)
	}
	private lateinit var compositeDisposable: CompositeDisposable

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
			Snackbar.make(binding.root, exception.toString(), Snackbar.LENGTH_INDEFINITE).show()
		})
		compositeDisposable.add(viewModel.successState
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe() { result ->
				binding.nameTextView.text = result[0].owner.login
				Picasso.with(requireContext())
					.load(result[0].owner.avatarUrl)
					.resize(500, 500)
					.centerCrop()
					.placeholder(R.drawable.ic_launcher_foreground)
					.into(binding.avatarImageView)
				adapter.setRepoList(result)
			})
		arguments?.let {
			it.getString(KEY_ACCOUNT)?.let {
				viewModel.getDataAccount(it)
			}
		}
	}
}