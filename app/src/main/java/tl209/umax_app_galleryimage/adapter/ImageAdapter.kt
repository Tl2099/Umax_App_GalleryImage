package tl209.umax_app_galleryimage.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tl209.umax_app_galleryimage.databinding.ItemImageHomeBinding

class ImageAdapter(
    private val images: List<Uri>,
    private val onItemClick: (Uri) -> Unit
): RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemImageHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    inner class ViewHolder(private val binding: ItemImageHomeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(uri: Uri){
            binding.imageHome.setImageURI(uri)
            //binding.tvName.
            binding.root.setOnClickListener{
                onItemClick(uri)
            }
        }
    }
}