package com.nsa.musicwiki.extra

import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nsa.musicwiki.R
import com.nsa.musicwiki.databinding.DescriptionBottomSheetLayoutBinding


class DescriptionBottomSheet(
                             private val title:String,
                            private val description:String
                      ) : BottomSheetDialogFragment() {


    private lateinit var binding: DescriptionBottomSheetLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.description_bottom_sheet_layout,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title.text=title
        binding.descriptionTv.text= Html.fromHtml(description)
        binding.closeTbn.setOnClickListener {
            dialog?.dismiss()
        }

    }


}