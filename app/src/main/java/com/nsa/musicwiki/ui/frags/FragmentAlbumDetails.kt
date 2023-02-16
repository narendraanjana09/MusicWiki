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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.nsa.musicwiki.R
import com.nsa.musicwiki.adapters.GenresAdapter
import com.nsa.musicwiki.databinding.FragmentAlbumDetailsBinding
import com.nsa.musicwiki.extra.DescriptionBottomSheet
import com.nsa.musicwiki.extra.Util
import com.nsa.musicwiki.models.Genre.Genre
import com.nsa.musicwiki.viewmodels.MainViewModel


class FragmentAlbumDetails : Fragment() {


    private lateinit var binding: FragmentAlbumDetailsBinding
    private val args: FragmentAlbumDetailsArgs by navArgs()

    private val genresList=arrayListOf<Genre>()
    private lateinit var genresAdapter: GenresAdapter

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
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_album_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.text=args.albumName
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        // Got this layout manager class from google flex-box library for genres chips
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        binding.genresRecyclerView.layoutManager = layoutManager

        //adding adapter to recycler view
         binding.genresRecyclerView.adapter=genresAdapter

        viewModel.loading.observe(viewLifecycleOwner){
            if(it){
                Util.showProgress(findNavController())
            }else{
                Util.hideProgress(findNavController(),R.id.fragmentAlbumDetails)
            }

        }
        viewModel.message.observe(viewLifecycleOwner){
            showToast(it)
        }
        viewModel.albumDetails.observe(viewLifecycleOwner){

            Util.loadImage(it.album.image[3].text,binding.imageView)

            //this conditions are used because we are getting null for certain albums wiki
            if(it.album.wiki!=null && !it.album.wiki.summary.isNullOrEmpty()){
                setDescriptionWithClick(binding.descriptionTv,it.album.wiki)
                binding.artistNameViewsTv.text="${it.album.artist} | ${Util.getPlayCountText(it.album.playcount.toLong())} | ${it.album.wiki.published}"
            }else{
                binding.artistNameViewsTv.text="${it.album.artist} | ${Util.getPlayCountText(it.album.playcount.toLong())}"
            }

            genresList.clear()
            it.album.genres.genre.forEach {
                genresList.add(Genre(0,it.name,0))
            }
            genresAdapter.notifyItemRangeInserted(0,it.album.genres.genre.size)
        }
    }

    //for adding color and clickable feature to subString(Read More) of text
    private fun setDescriptionWithClick(textView: TextView, summary: com.nsa.musicwiki.models.albumDetails.Wiki) {
        val readMoreText=resources.getString(R.string.read_more)
         if(summary.summary.length>100){
           val  content = summary.summary.substring(0,100)+"...$readMoreText"
            val clickableSpan = object: ClickableSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = ContextCompat.getColor(requireContext(), R.color.red)
                }
                override fun onClick(textView: View) {
                    val modalBottomSheet = DescriptionBottomSheet(args.albumName,summary.content)
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
        genresAdapter= GenresAdapter(genresList,object :GenresAdapter.ClickCallBack{
            override fun onClicked(position: Int) {
                
                findNavController().navigate(
                    FragmentAlbumDetailsDirections.actionFragmentAlbumDetailsToFragmentGenreDetails(genresList[position].name)
                )
            }
        })


        viewModel.getAlbumDetails(args.artistName,args.albumName)
    }

}