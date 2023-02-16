package com.nsa.musicwiki.adapters

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.nsa.musicwiki.R
import com.nsa.musicwiki.databinding.AlbumsRecyclerItemBinding
import com.nsa.musicwiki.databinding.ArtistsRecyclerItemBinding
import com.nsa.musicwiki.databinding.TracksRecyclerItemBinding
import com.nsa.musicwiki.extra.Util
import com.nsa.musicwiki.models.topAlbums.Album
import com.nsa.musicwiki.models.topArtists.Artist
import com.nsa.musicwiki.models.topTracks.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//using this adapter to display list of top artists under selected genre
class ArtistsAdapter (private val mList: List<Artist>,
                      private val clickCallback: ClickCallBack
) : RecyclerView.Adapter<ArtistsAdapter.ViewHolder>() {

    interface ClickCallBack{
        fun onClicked(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ArtistsRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder.setData(item)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(private val binding: ArtistsRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: Artist) {
            binding.apply {
               artistNameTv.text=item.name
            }
            Util.loadImage(item.image[2].text,binding.imageView)
            binding.root.setOnClickListener {
                clickCallback.onClicked(adapterPosition)
            }
        }
    }
}