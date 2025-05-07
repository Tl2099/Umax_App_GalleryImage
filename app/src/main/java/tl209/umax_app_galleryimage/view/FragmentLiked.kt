package tl209.umax_app_galleryimage.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import tl209.umax_app_galleryimage.adapter.ImageLikedAdapter
import tl209.umax_app_galleryimage.databinding.FragmentLikedBinding
import tl209.umax_app_galleryimage.viewmodel.LikedImageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentLiked: Fragment() {
    private lateinit var binding: FragmentLikedBinding
    private val viewModel: LikedImageViewModel by viewModel()
    private lateinit var imageLikedAdapter: ImageLikedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeLikedImages()
    }

    private fun observeLikedImages() {
        viewModel.likedImages.observe(viewLifecycleOwner) { likedImages ->
            Log.d("Liked", "$likedImages")
            imageLikedAdapter = ImageLikedAdapter(requireContext(), likedImages)
            binding.recycleView.adapter = imageLikedAdapter
        }
    }


    private fun setupRecyclerView() {
        val spacingInPixels = 16
        binding.recycleView.addItemDecoration(
            GridSpacingItemDecoration(
                3,
                spacingInPixels,
                true
            )
        )
        binding.recycleView.layoutManager = GridLayoutManager(requireContext(), 2)
        imageLikedAdapter = ImageLikedAdapter(requireContext(), emptyList())
        binding.recycleView.adapter = imageLikedAdapter
    }
}