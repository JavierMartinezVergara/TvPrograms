package com.example.tvprograms.ui.homeprograms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.tvprograms.R
import com.example.tvprograms.data.remote.responses.Programs
import com.example.tvprograms.databinding.ItemProgramsBinding
import kotlinx.android.synthetic.main.item_programs.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProgramsAdapter (private val listener: (Programs) -> Unit)
    : ListAdapter<Programs, RecyclerView.ViewHolder>(Dif()) {

    private lateinit var context: Context
    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderandSubmitList(list : List<Programs>?){

        adapterScope.launch {
            withContext(Dispatchers.Main){
                submitList(list)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ListViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ListViewHolder -> {
                val item = getItem(position)
                holder.bind(item)
            }
        }


    }
}

class ListViewHolder(view: View): RecyclerView.ViewHolder(view) {
    companion object {
        fun from(parent: ViewGroup): ListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_programs, parent, false)
            return ListViewHolder(view)
        }

    }

    fun bind(
            item: Programs
    ) {
        itemView.tvTitleProgram.text = item.name
        itemView.tvNetworkNameProgram.text = item.show.network?.name
        itemView.tvInfoProgram.text = "${item.airdate} ! ${item.airtime}"
        itemView.imgDetail.load(item.show.image?.medium){
            crossfade(true)
            crossfade(50)
            transformations(RoundedCornersTransformation(20f))
        }
    }
}




class Dif : DiffUtil.ItemCallback<Programs>() {
    override fun areItemsTheSame(oldItem: Programs, newItem: Programs): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Programs, newItem: Programs): Boolean {
        return oldItem == newItem
    }


}

