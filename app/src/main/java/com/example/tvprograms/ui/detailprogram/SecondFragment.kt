package com.example.tvprograms.ui.detailprogram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.tvprograms.R
import com.example.tvprograms.databinding.FragmentSecondBinding
import com.example.tvprograms.ui.ProgramsViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private val viewModel : ProgramsViewModel by activityViewModels()
    private lateinit var binding : FragmentSecondBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProgramDetail("2")
        subscribeLiveData()


    }

    private fun subscribeLiveData(){
        viewModel.programsDetailData.observe(viewLifecycleOwner, {
            binding.tvTitleProgram.text = it.network.name
            binding.tvNetworkNameProgram.text = it.name
            binding.tvRatingProgram.text = requireContext().getString(R.string.rating).plus(it.rating.average)
            binding.tvSipnosisProgram.text = requireContext().getString(R.string.sinopsis).plus(it.summary)
            binding.tvGenres.text = requireContext().getString(R.string.genres).plus(it.genres)
            binding.tvSchedules.text = requireContext().getString(R.string.squedule).plus(it.schedule.time).plus(it.schedule.days)
            binding.imgDetail.load(it.image.medium){
                crossfade(true)
                crossfade(100)
                transformations(RoundedCornersTransformation(20f))
            }
        })
    }
}