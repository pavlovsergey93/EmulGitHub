package com.gmail.pavlovsv93.emulgithub.ui.details.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.app
import com.gmail.pavlovsv93.emulgithub.databinding.FragmentDetailsAccountBinding
import com.gmail.pavlovsv93.emulgithub.ui.ViewModel
import com.google.android.material.snackbar.Snackbar
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
		ViewModel(requireActivity().app.repo)
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
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val recyclerView: RecyclerView = binding.listRepoRecyclerView
		recyclerView.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
		recyclerView.adapter = adapter

		compositeDisposable.add(viewModel.processState.subscribe() { shouldShow ->
			with(requireActivity().findViewById<ProgressBar>(R.id.main_progress_bar)) {
				visibility = if (shouldShow) {
					View.VISIBLE
				} else {
					View.GONE
				}
			}
		})
		compositeDisposable.add(viewModel.errorState.subscribe() { exception ->
			Snackbar.make(binding.root, exception.toString(), Snackbar.LENGTH_INDEFINITE).show()
		})
		arguments?.let {
			it.getString(KEY_ACCOUNT)?.let {
				compositeDisposable.add(viewModel.successState.subscribe() { result ->
					binding.nameTextView.text = result.login
					binding.avatarImageView.setImageResource(R.drawable.ic_launcher_foreground)
					adapter.setRepoList(result.list)
				})
			}
		}
	}
}