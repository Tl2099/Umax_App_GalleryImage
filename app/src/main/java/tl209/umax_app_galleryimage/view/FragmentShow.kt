package tl209.umax_app_galleryimage.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import tl209.umax_app_galleryimage.databinding.FragmentShowBinding
import tl209.umax_app_galleryimage.viewmodel.LikedImageViewModel
import java.io.File
import org.koin.androidx.viewmodel.ext.android.viewModel
import tl209.umax_app_galleryimage.R

class FragmentShow: Fragment() {
    private lateinit var binding: FragmentShowBinding
    private val viewModel: LikedImageViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = FragmentShowArgs.fromBundle(requireArguments())
        Log.d("Test", "$args")
        args.imgPath.let {
            Glide.with(this).load(File(it)).into(binding.imageView)
        }
        val imgPath = args.imgPath

        //lifecycleScope.launch {
            viewModel.isImageLiked(imgPath).observe(viewLifecycleOwner) { isLiked ->
                binding.btLike.visibility = if(isLiked) View.GONE else View.VISIBLE
                binding.btDisLike.visibility = if (isLiked) View.VISIBLE else View.GONE
            }
        //}

        viewModel.likedImages.observe(viewLifecycleOwner) {likedList ->
            val imageNames = likedList.map { File(it.imagePath).name }
            Log.d("Name", imageNames.joinToString())
        }
        binding.btBack.setOnClickListener{
            val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNav.visibility = View.VISIBLE

            findNavController().navigateUp()
            //cách 2:
            //requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.btLike.setOnClickListener{
            viewModel.likeImage(imgPath)
        }

        binding.btDisLike.setOnClickListener{
            viewModel.unlikeImage(imgPath)
        }
    }
}