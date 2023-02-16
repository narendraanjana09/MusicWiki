package com.nsa.musicwiki.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nsa.musicwiki.databinding.AlbumsRecyclerItemBinding
import com.nsa.musicwiki.databinding.ArtistTopTracksAlbumsRecyclerItemBinding
import com.nsa.musicwiki.extra.Util

//using this adapter to display list of top albums for selected artist
class ArtistTopAlbumsAdapter(private val mList: ArrayList<com.nsa.musicwiki.models.artistTopAlbums.Album>,
                             private val clickCallback: ClickCallBack
) : RecyclerView.Adapter<ArtistTopAlbumsAdapter.ViewHolder>() {

    interface ClickCallBack{
        fun onClicked(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ArtistTopTracksAlbumsRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder.setData(item)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(private val binding: ArtistTopTracksAlbumsRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: com.nsa.musicwiki.models.artistTopAlbums.Album) {
            binding.apply {
               nameTv.text=item.name
            }
            Util.loadImage(item.image[2].text,binding.imageView)
            binding.root.setOnClickListener {
                clickCallback.onClicked(adapterPosition)
            }
        }
    }
}