package com.example.socialnet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.socialnet.adapters.POSTS_LIST_PAGE_INDEX
import com.example.socialnet.databinding.FragmentSavedPostsBinding
import com.example.socialnet.viewmodels.SavedPostsListViewModel

class SavedPostsFragment : Fragment() {

    private lateinit var binding: FragmentSavedPostsBinding

    private val viewModel: SavedPostsListViewModel by viewModels {
        InjectorUtils.provideGardenPlantingListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedPostsBinding.inflate(inflater, container, false)
        val adapter = GardenPlantingAdapter()
        binding.gardenList.adapter = adapter

        binding.addPost.setOnClickListener {
            navigateToPlantListPage()
        }

        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: GardenPlantingAdapter, binding: FragmentGardenBinding) {
        viewModel.plantAndGardenPlantings.observe(viewLifecycleOwner) { result ->
            binding.hasPlantings = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }

    // TODO: convert to data binding if applicable
    private fun navigateToAllPostsListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
            POSTS_LIST_PAGE_INDEX
    }

}