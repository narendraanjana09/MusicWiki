package com.nsa.musicwiki.ui.frags

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.nsa.musicwiki.R
import com.nsa.musicwiki.adapters.FragmentsPagerAdapter
import com.nsa.musicwiki.databinding.FragmentGenreDetailsBinding
import com.nsa.musicwiki.extra.DescriptionBottomSheet
import com.nsa.musicwiki.extra.FragmentDetailsViewPagerCallbacks
import com.nsa.musicwiki.extra.Util
import com.nsa.musicwiki.models.GenreDetail.Wiki
import com.nsa.musicwiki.viewmodels.MainViewModel


class FragmentGenreDetails : Fragment() {


    private lateinit var binding: FragmentGenreDetailsBinding
    private val args: FragmentGenreDetailsArgs by navArgs()

    private val tabsList= arrayListOf<String>()

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
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_genre_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.text=args.genreName
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        //adding pager adapter to view pager and connecting view pager with tablayout
        addViewPagerWithTabLayout()

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
        viewModel.genresInfo.observe(viewLifecycleOwner){
            if(!it.wiki.summary.isNullOrEmpty()){
                setDescriptionWithClick(binding.descriptionTv,it.wiki)
            }
        }




    }

    private fun addViewPagerWithTabLayout() {
        binding.viewPager.adapter=FragmentsPagerAdapter(this,args.genreName,object :FragmentDetailsViewPagerCallbacks{
            override fun onTopAlbumClick(albumName: String, artistName: String) {
                findNavController().navigate(
                    FragmentGenreDetailsDirections.actionFragmentGenreDetailsToFragmentAlbumDetails(albumName,artistName)
                )
            }

            override fun onTopArtistClick(artistName: String) {
                findNavController().navigate(
                    FragmentGenreDetailsDirections.actionFragmentGenreDetailsToFragmentArtistDetails(artistName)

                )
            }
        })
        tabsList.add(resources.getString(R.string.top_albums))
        tabsList.add(resources.getString(R.string.top_tracks))
        tabsList.add(resources.getString(R.string.top_artists))
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabsList[position]
        }.attach()
        for (tabIndex in 0 until binding.tabLayout.tabCount) {
            val tabTextView =
                ((binding.tabLayout.getChildAt(0) as LinearLayout).getChildAt(tabIndex) as LinearLayout).getChildAt(
                    1
                ) as TextView
            tabTextView.isAllCaps = false
        }
    }

    //for adding color and clickable feature to subString(Read More) of text
    private fun setDescriptionWithClick(textView: TextView, summary: Wiki) {
        val readMoreText=resources.getString(R.string.read_more)
         if(summary.summary.length>150){
            val content =summary.summary.substring(0,150)+"...$readMoreText"
            val clickableSpan = object: ClickableSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = ContextCompat.getColor(requireContext(), R.color.red)
                }
                override fun onClick(textView: View) {
                    val modalBottomSheet = DescriptionBottomSheet(args.genreName,summary.content)
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

        viewModel.getGenreInfo(args.genreName)

    }

}