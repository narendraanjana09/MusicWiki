package com.nsa.musicwiki.ui.frags.genreTopFrags

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nsa.musicwiki.R
import com.nsa.musicwiki.adapters.AlbumsAdapter
import com.nsa.musicwiki.adapters.TracksAdapter
import com.nsa.musicwiki.databinding.FragmentForTopAllBinding
import com.nsa.musicwiki.extra.Util
import com.nsa.musicwiki.models.topAlbums.Album
import com.nsa.musicwiki.models.topTracks.Track
import com.nsa.musicwiki.viewmodels.MainViewModel


class FragmentTopTracks(private val genreName: String) : Fragment() {


    private lateinit var binding:FragmentForTopAllBinding
    private val tracksList=arrayListOf<Track>()
    private lateinit var trackAdapter: TracksAdapter

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

        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=trackAdapter

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

        viewModel.topTracks.observe(viewLifecycleOwner){
            tracksList.clear()
            tracksList.addAll(it.tracks.track)
            trackAdapter.notifyItemRangeInserted(0,it.tracks.track.size)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initializing adapter
        trackAdapter= TracksAdapter(tracksList,object :TracksAdapter.ClickCallBack{
            override fun onClicked(position: Int) {
            }
        })

        viewModel.getTopTracks(genreName)
    }
}