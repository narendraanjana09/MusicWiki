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
import com.nsa.musicwiki.R
import com.nsa.musicwiki.adapters.ArtistsAdapter
import com.nsa.musicwiki.databinding.FragmentForTopAllBinding
import com.nsa.musicwiki.extra.FragmentDetailsViewPagerCallbacks
import com.nsa.musicwiki.extra.Util
import com.nsa.musicwiki.models.topArtists.Artist
import com.nsa.musicwiki.viewmodels.MainViewModel


class FragmentTopArtists(
    private val genreName: String,
    private val fragmentDetailsViewPagerCallbacks: FragmentDetailsViewPagerCallbacks
) : Fragment() {


    private lateinit var binding:FragmentForTopAllBinding

    private val artistsList=arrayListOf<Artist>()
    private lateinit var artistsAdapter: ArtistsAdapter


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


        binding.recyclerView.layoutManager= GridLayoutManager(requireContext(),2,)
        binding.recyclerView.adapter=artistsAdapter

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
        viewModel.topArtists.observe(viewLifecycleOwner){
            artistsList.clear()
            artistsList.addAll(it.topartists.artist)
            artistsAdapter.notifyItemRangeInserted(0,it.topartists.artist.size)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initializing adapter
        artistsAdapter= ArtistsAdapter(artistsList,object :ArtistsAdapter.ClickCallBack{
            override fun onClicked(position: Int) {
                fragmentDetailsViewPagerCallbacks.onTopArtistClick(artistsList[position].name)

            }
        })

        viewModel.getTopArtists(genreName)
    }
}