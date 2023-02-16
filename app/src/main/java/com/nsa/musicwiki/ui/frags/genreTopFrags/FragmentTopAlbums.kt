package com.nsa.musicwiki.ui.frags.genreTopFrags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nsa.musicwiki.R
import com.nsa.musicwiki.adapters.AlbumsAdapter
import com.nsa.musicwiki.databinding.FragmentForTopAllBinding
import com.nsa.musicwiki.extra.FragmentDetailsViewPagerCallbacks
import com.nsa.musicwiki.extra.Util
import com.nsa.musicwiki.models.topAlbums.Album
import com.nsa.musicwiki.viewmodels.MainViewModel


class FragmentTopAlbums(
    private val genreName: String,
    private val fragmentDetailsViewPagerCallbacks: FragmentDetailsViewPagerCallbacks
) : Fragment() {


    private lateinit var binding:FragmentForTopAllBinding
    private val albumsList=arrayListOf<Album>()
    private lateinit var albumsAdapter: AlbumsAdapter


    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun showToast(message:String){
        Util.showToast(requireContext(),message)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_for_top_all, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager=GridLayoutManager(requireContext(),2,)
        binding.recyclerView.adapter=albumsAdapter

        viewModel.loading.observe(viewLifecycleOwner){
            if(it){
                Util.showProgress(findNavController())
            }else{
                Util.hideProgress(findNavController(),R.id.fragmentGenreDetails)
            }

        }
        viewModel.message.observe(viewLifecycleOwner){
            showToast(it)
        }
        viewModel.topAlbums.observe(viewLifecycleOwner){
            albumsList.clear()
            albumsList.addAll(it.albums.album)
            albumsAdapter.notifyItemRangeInserted(0,it.albums.album.size)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initializing adapter
        albumsAdapter= AlbumsAdapter(albumsList,object :AlbumsAdapter.ClickCallBack{
            override fun onClicked(position: Int) {
                fragmentDetailsViewPagerCallbacks
                    .onTopAlbumClick(albumsList[position].name,albumsList[position].artist.name)
            }
        })

        viewModel.getTopAlbums(genreName)
    }
}