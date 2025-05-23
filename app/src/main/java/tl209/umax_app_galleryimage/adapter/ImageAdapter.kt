package tl209.umax_app_galleryimage.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tl209.umax_app_galleryimage.R
import tl209.umax_app_galleryimage.databinding.ItemImageHomeBinding
import tl209.umax_app_galleryimage.view.FragmentHomeDirections
import tl209.umax_app_galleryimage.view.FragmentShow
import java.io.File

class ImageAdapter(
    private val context: Context,
    private val imagePathArrayList: List<String>,
    private val onItemClick: (String) -> Unit
): RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemImageHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return imagePathArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imagePathArrayList[position])
    }

    inner class ViewHolder(private val binding: ItemImageHomeBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(imagePath: String){
            val imgFile = File(imagePath)
            if(imgFile.exists()){
                Glide.with(context).load(imgFile).placeholder(R.drawable.meow1).into(binding.imageHome)
            }
            binding.tvName.text = "Ảnh ${adapterPosition + 1}"
            binding.root.setOnClickListener{
                onItemClick(imagePath)
            }
        }
    }
}