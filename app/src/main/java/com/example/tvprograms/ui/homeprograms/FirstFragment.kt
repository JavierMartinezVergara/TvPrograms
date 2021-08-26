package com.example.tvprograms.ui.homeprograms

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvprograms.R
import com.example.tvprograms.data.local.ProgramsLocal
import com.example.tvprograms.data.local.Type
import com.example.tvprograms.data.remote.responses.Programs
import com.example.tvprograms.databinding.FragmentFirstBinding
import com.example.tvprograms.ui.ProgramsViewModel
import com.example.tvprograms.ui.detailprogram.SecondFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_error.view.*
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

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeLiveData()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_search, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView

        searchView.setOnCloseListener {
            val date: String = SimpleDateFormat("yyyy-MM-dd").format(Date())
            viewModel.getPrograms("US", date)
            true
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.delete()
                    viewModel.getShowList(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //viewModel.delete()
                return true
            }

        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_search ->{
                viewModel.delete()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeLiveData(){
        viewModel.programsLiveData.observe(viewLifecycleOwner, {
            initList(it)
        })

        viewModel.programsShowListData.observe(viewLifecycleOwner, {
            initList(it)

        })

        viewModel.loading.observe(viewLifecycleOwner, {
            when(it){
                true -> binding.progressCircular.visibility = View.VISIBLE
                false -> binding.progressCircular.visibility = View.GONE
            }
        })

        viewModel.error.observe(viewLifecycleOwner,){
            if(it.isEmpty()){
                binding.viewEmpty.containerRoot.visibility = View.GONE
                binding.viewEmpty.containerRoot.tvNoMovements.text = it
            } else {
                binding.viewEmpty.containerRoot.visibility = View.VISIBLE
            }
        }
    }

    private fun initList(items: List<ProgramsLocal>) {
        adapterPrograms = ProgramsAdapter {
            when(it.type){
                Type.PROGRAM.name ->{
                }
                else ->{
                    val bundle = bundleOf("id" to it.id.toString())
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
                }
            }

        }
        binding.rvPrograms.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterPrograms
        }
        adapterPrograms.addHeaderandSubmitList(items)


    }
}