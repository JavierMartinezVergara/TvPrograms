package com.example.tvprograms.ui.detailprogram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.tvprograms.R
import com.example.tvprograms.data.local.ProgramsLocal
import com.example.tvprograms.data.local.TalentLocal
import com.example.tvprograms.data.local.Type
import com.example.tvprograms.databinding.FragmentSecondBinding
import com.example.tvprograms.ui.ProgramsViewModel
import com.example.tvprograms.ui.homeprograms.ProgramsAdapter

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private val viewModel : ProgramsViewModel by activityViewModels()
    private lateinit var binding : FragmentSecondBinding
    private lateinit var adapterTalent: TalentAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val argument = arguments?.getString("id")
        argument?.let {
            viewModel.getProgramDetail(it)
            viewModel.getTalents(it)
        }
        subscribeLiveData()


    }

    private fun subscribeLiveData(){
        viewModel.programsDetailData.observe(viewLifecycleOwner, {
            binding.tvTitleProgram.text = it.network.name
            binding.tvNetworkNameProgram.text = it.name
            binding.tvRatingProgram.text = resources.getString(
                R.string.rating,it.rating)
            binding.tvSipnosisProgram.text = resources.getString(
                R.string.sinopsis,it.summary)
            binding.tvGenres.text = resources.getString(
                R.string.genres,it.genres)
            binding.tvSchedules.text = resources.getString(
                R.string.squedule,it.schedule.days)
            binding.imgDetail.load(it.image.medium){
                crossfade(true)
                crossfade(100)
                transformations(RoundedCornersTransformation(20f))
            }
        })

        viewModel.talentsLiveData.observe(viewLifecycleOwner, {
            if(it.isNotEmpty()){
                initListTalent(it)
            }

        })
    }

    private fun initListTalent(items: List<TalentLocal>) {
        adapterTalent = TalentAdapter {
        }
        binding.rvTalentsProgram.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false )
            adapter = adapterTalent
        }
        adapterTalent.addHeaderandSubmitList(items)


    }
}