package com.nsa.musicwiki.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nsa.musicwiki.databinding.GenreChipItemBinding
import com.nsa.musicwiki.models.Genre.Genre

//using this adapter to display list of genres as chips layout
class GenresAdapter (private val mList: List<Genre>,
                     private val clickCallback: ClickCallBack
) : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    interface ClickCallBack{
        fun onClicked(position: Int)
    }
    private var viewAll=false

    fun viewAll(viewAll:Boolean){
        this.viewAll=viewAll
    }
    fun getViewAll(): Boolean {
        return viewAll
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=GenreChipItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder.setData(item)
    }

    override fun getItemCount(): Int {
        return if(viewAll){
            mList.size
        }else{
            if(mList.size<=10){
                mList.size
            }else{
                10
            }
        }
    }

    inner class ViewHolder(private val binding: GenreChipItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: Genre) {
            binding.apply {
               chip.text=item.name
            }
            binding.chip.setOnClickListener {
                clickCallback.onClicked(adapterPosition)
            }
        }
    }
}