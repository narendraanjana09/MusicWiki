package com.nsa.musicwiki.ui.frags

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nsa.musicwiki.R
import com.nsa.musicwiki.adapters.ArtistTopAlbumsAdapter
import com.nsa.musicwiki.adapters.ArtistTopTracksAdapter
import com.nsa.musicwiki.adapters.GenresAdapter
import com.nsa.musicwiki.databinding.FragmentArtistDetailsBinding
import com.nsa.musicwiki.extra.DescriptionBottomSheet
import com.nsa.musicwiki.extra.Util
import com.nsa.musicwiki.models.Genre.Genre
import com.nsa.musicwiki.models.artistDetails.Bio
import com.nsa.musicwiki.models.artistTopAlbums.Album
import com.nsa.musicwiki.models.artistTopTracks.Track
import com.nsa.musicwiki.viewmodels.MainViewModel


class FragmentArtistDetails : Fragment() {


    private lateinit var binding: FragmentArtistDetailsBinding
    private val args: FragmentArtistDetailsArgs by navArgs()

    private val genresList=arrayListOf<Genre>()
    private lateinit var genresAdapter: GenresAdapter

    private val tracksList=arrayListOf<Track>()
    private lateinit var artistTopTracksAdapter: ArtistTopTracksAdapter

    private val albumsList=arrayListOf<Album>()
    private lateinit var artistTopAlbumsAdapter:  ArtistTopAlbumsAdapter

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
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_artist_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.text=args.artistName
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        //adding adapter to recycler view
         binding.genresRecyclerView.adapter=genresAdapter
        binding.tracksRecyclerView.adapter=artistTopTracksAdapter
        binding.albumsRecyclerView.adapter=artistTopAlbumsAdapter

        viewModel.loading.observe(viewLifecycleOwner){
            if(it){
                Util.showProgress(findNavController())
            }else{
                Util.hideProgress(findNavController(),R.id.fragmentArtistDetails)
            }

        }
        viewModel.message.observe(viewLifecycleOwner){
            showToast(it)
        }
        viewModel.artistDetails.observe(viewLifecycleOwner){

            Util.loadImage(it.artist.image[3].text,binding.imageView)

            if(!it.artist.bio.summary.isNullOrEmpty()){
                setDescriptionWithClick(binding.descriptionTv,it.artist.bio)
            }
            binding.followeresTv.text="${Util.getPlayCountText(it.artist.stats.listeners.toLong())}"
            binding.playCountTv.text="${Util.getPlayCountText(it.artist.stats.playcount.toLong())}"

            genresList.clear()
            it.artist.genres.genre.forEach {
                genresList.add(Genre(0,it.name,0))
            }
            genresAdapter.notifyItemRangeInserted(0,it.artist.genres.genre.size)
        }

        viewModel.artistTopTracks.observe(viewLifecycleOwner){
            tracksList.clear()
            tracksList.addAll(it.toptracks.track)
            artistTopTracksAdapter.notifyItemRangeInserted(0,it.toptracks.track.size)
        }

        viewModel.artistTopAlbums.observe(viewLifecycleOwner){
            albumsList.clear()
            albumsList.addAll(it.topalbums.album)
            artistTopAlbumsAdapter.notifyItemRangeInserted(0,it.topalbums.album.size)
        }

    }

    //for adding color and clickable feature to subString(Read More) of text
    private fun setDescriptionWithClick(textView: TextView, summary: Bio) {
        val readMoreText=resources.getString(R.string.read_more)
         if(summary.summary.length>80){
           val  content = summary.summary.substring(0,80)+"...$readMoreText"
            val clickableSpan = object: ClickableSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = ContextCompat.getColor(requireContext(), R.color.red)
                }
                override fun onClick(textView: View) {
                    val modalBottomSheet = DescriptionBottomSheet(args.artistName,summary.content)
                    modalBottomSheet.show(requireActivity().supportFragmentManager, "bottom")
                }
            }
            val startIndex = content.indexOf(readMoreText)
            val endIndex = startIndex + readMoreText.length
            val spannableString = SpannableString(content)
            spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            textView.text = spannableString
            textView.movementMethod = LinkMovementMethod.getInstance()
            textView.highlightColor = Color.TRANSPARENT
        }else{
           textView.text= Html.fromHtml(summary.summary)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initializing adapter
        artistTopTracksAdapter= ArtistTopTracksAdapter(tracksList,object :ArtistTopTracksAdapter.ClickCallBack{
            override fun onClicked(position: Int) {

            }
        })

        artistTopAlbumsAdapter= ArtistTopAlbumsAdapter(albumsList,object :ArtistTopAlbumsAdapter.ClickCallBack{
            override fun onClicked(position: Int) {
                findNavController().navigate(
                    FragmentArtistDetailsDirections.actionFragmentArtistDetailsToFragmentAlbumDetails(albumsList[position].name,args.artistName)
                )
            }
        })
        
        genresAdapter= GenresAdapter(genresList,object :GenresAdapter.ClickCallBack{
            override fun onClicked(position: Int) {
                findNavController().navigate(
                    FragmentArtistDetailsDirections.actionFragmentArtistDetailsToFragmentGenreDetails(genresList[position].name)
                )
            }
        })


        viewModel.getArtistTopTracks(args.artistName)
        viewModel.getArtistTopAlbums(args.artistName)
        viewModel.getArtistDetails(args.artistName)
    }

}