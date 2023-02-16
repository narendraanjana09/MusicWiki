package com.nsa.musicwiki.ui.frags


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.nsa.musicwiki.R
import com.nsa.musicwiki.adapters.GenresAdapter
import com.nsa.musicwiki.databinding.FragmentGenresBinding
import com.nsa.musicwiki.extra.Util
import com.nsa.musicwiki.models.Genre.Genre
import com.nsa.musicwiki.viewmodels.MainViewModel


class FragmentGenres : Fragment() {


    private lateinit var binding: FragmentGenresBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private val genresList=arrayListOf<Genre>()
    private lateinit var genresAdapter: GenresAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_genres, container, false)

        return binding.root;
    }

    private fun showToast(message:String){
        Util.showToast(requireContext(),message)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       // Got this layout manager class from google flex-box library
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        binding.genresRecyclerView.layoutManager = layoutManager

        //adding adapter to recycler view
        binding.genresRecyclerView.adapter=genresAdapter

        viewModel.loading.observe(viewLifecycleOwner){
            if(it){
                Util.showProgress(findNavController())
            }else{
                Util.hideProgress(findNavController(),R.id.fragmentGenres)
            }

        }
        viewModel.message.observe(viewLifecycleOwner){
            showToast(it)
        }

        viewModel.genresList.observe(viewLifecycleOwner){

            genresList.clear()
            genresList.addAll(it)
            genresAdapter.notifyItemRangeInserted(0,10)

        }


        //for changing size of tags from 10 to available size
        binding.viewAllBtn.setOnClickListener {
            if(genresAdapter.getViewAll()){
                binding.viewAllBtn.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.arrow_down))
            }else{
                binding.viewAllBtn.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.arrow_up))
            }
            genresAdapter.viewAll(viewAll = !genresAdapter.getViewAll())
            genresAdapter.notifyDataSetChanged()
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initializing adapter
        genresAdapter= GenresAdapter(genresList,object :GenresAdapter.ClickCallBack{
            override fun onClicked(position: Int) {
                
                findNavController().navigate(
                    FragmentGenresDirections.actionFragmentGenresToFragmentGenreDetails(genresList[position].name)
                )
            }
        })

        viewModel.getTopGenres()



    }


}