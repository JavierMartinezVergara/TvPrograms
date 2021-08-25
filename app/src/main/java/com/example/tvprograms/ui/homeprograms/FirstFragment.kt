package com.example.tvprograms.ui.homeprograms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvprograms.R
import com.example.tvprograms.data.remote.responses.Programs
import com.example.tvprograms.databinding.FragmentFirstBinding
import com.example.tvprograms.ui.ProgramsViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val viewModel : ProgramsViewModel by activityViewModels()
    private lateinit var binding : FragmentFirstBinding
    private lateinit var adapterPrograms: ProgramsAdapter
    private val layoutManager by lazy { (LinearLayoutManager(requireContext())) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_first, container, false)
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeLiveData()

        binding.buttonFirst.setOnClickListener {
            viewModel.getPrograms("US", "2020-07-05")
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }




    }

    private fun subscribeLiveData(){
        viewModel.programsLiveData.observe(viewLifecycleOwner, {
            if(it.isNotEmpty()){
                initList(it)
            }
        })
    }

    private fun initList(items: List<Programs>) {
        adapterPrograms = ProgramsAdapter {  }
        binding.rvPrograms.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterPrograms
        }
        adapterPrograms.addHeaderandSubmitList(items)


    }
}