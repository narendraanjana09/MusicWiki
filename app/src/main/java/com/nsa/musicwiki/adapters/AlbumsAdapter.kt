package com.nsa.musicwiki.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.nsa.musicwiki.R
import com.nsa.musicwiki.databinding.AlbumsRecyclerItemBinding
import com.nsa.musicwiki.extra.Util
import com.nsa.musicwiki.models.topAlbums.Album
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//using this adapter to display list of top albums under selected genre
class AlbumsAdapter (private val mList: List<Album>,
                     private val clickCallback: ClickCallBack
) : RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    interface ClickCallBack{
        fun onClicked(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=AlbumsRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder.setData(item)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(private val binding: AlbumsRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: Album) {
            binding.apply {
               albumNameTv.text=item.name
                artistNameTv.text=item.artist.name
            }
            Util.loadImage(item.image[2].text,binding.imageView)
            binding.root.setOnClickListener {
                clickCallback.onClicked(adapterPosition)
            }
        }
    }
}