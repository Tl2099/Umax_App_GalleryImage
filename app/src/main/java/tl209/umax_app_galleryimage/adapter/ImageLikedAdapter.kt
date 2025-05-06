package tl209.umax_app_galleryimage.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tl209.umax_app_galleryimage.R
import tl209.umax_app_galleryimage.databinding.ItemImageHomeBinding
import tl209.umax_app_galleryimage.model.LikedImage
import java.io.File

class ImageLikedAdapter(
    private val context: Context,
    private var imageList: List<LikedImage>, // Thay đổi sang LikedImage
): RecyclerView.Adapter<ImageLikedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemImageHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
    fun submitList(images: List<LikedImage>) {
        this.imageList = images
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageList[position])  // Truyền LikedImage thay vì String
    }

    inner class ViewHolder(private val binding: ItemImageHomeBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(likedImage: LikedImage) {
            val imgFile = File(likedImage.imagePath)  // Lấy đường dẫn ảnh từ LikedImage
            if (imgFile.exists()) {
                Glide.with(context).load(imgFile).placeholder(R.drawable.meow1).into(binding.imageHome)
            }
            binding.tvName.text = "Ảnh ${adapterPosition + 1}"
        }
    }
}