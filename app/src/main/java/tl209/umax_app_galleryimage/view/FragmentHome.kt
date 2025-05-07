package tl209.umax_app_galleryimage.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import tl209.umax_app_galleryimage.R
import tl209.umax_app_galleryimage.adapter.ImageAdapter
import tl209.umax_app_galleryimage.databinding.FragmentHomeBinding
import tl209.umax_app_galleryimage.viewmodel.ImageViewModel

class FragmentHome: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: ImageViewModel by activityViewModels()

    private val imageList = listOf(
        R.drawable.meow1,
        R.drawable.meow2,
        R.drawable.meow3,
        R.drawable.meow4,
        R.drawable.meow5,
        R.drawable.meow6,
        R.drawable.meow7,
        R.drawable.meow8,
        R.drawable.meow9,
        R.drawable.meow10,
        R.drawable.meow11,
        R.drawable.meow12,
        R.drawable.meow13,
        R.drawable.meow14,
        R.drawable.meow15
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        checkPermissionApp()

        observeImages()

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
        binding.recycleView.layoutManager = GridLayoutManager(requireContext(), 3)

    }

    private fun observeImages() {
        viewModel.imageList.observe(viewLifecycleOwner){ imageList ->
            binding.recycleView.adapter = ImageAdapter(requireContext(), imageList){ path ->
                val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
                bottomNav.visibility = View.GONE

                val action = FragmentHomeDirections.actionHomeFragToShowFrag(path)
                Log.d("Test0", "$path")
                findNavController().navigate(action)
            }
        }
    }

    private fun checkPermissionApp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
                viewModel.loadImages()
            } else {
                requestPermissions(arrayOf(Manifest.permission.READ_MEDIA_IMAGES), 100)
            }
        } else {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                viewModel.loadImages()
            } else {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 100)
            }
        }
    }
}