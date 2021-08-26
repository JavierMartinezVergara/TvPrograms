package com.example.tvprograms.ui.detailprogram

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
import com.example.tvprograms.data.local.TalentLocal
import kotlinx.android.synthetic.main.item_talent.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TalentAdapter (private val listener: (TalentLocal) -> Unit)
    : ListAdapter<TalentLocal, RecyclerView.ViewHolder>(Dif()) {

    private lateinit var context: Context
    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderandSubmitList(list : List<TalentLocal>?){

        adapterScope.launch {
            withContext(Dispatchers.Main){
                submitList(list)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return TalentViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TalentViewHolder -> {
                val item = getItem(position)
                holder.bind(item)
                holder.itemView.setOnClickListener { listener(item) }
            }
        }


    }
}

class TalentViewHolder(view: View): RecyclerView.ViewHolder(view) {
    companion object {
        fun from(parent: ViewGroup): TalentViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_talent, parent, false)
            return TalentViewHolder(view)
        }

    }

    fun bind(
            item: TalentLocal
    ) {
        itemView.tvNameTalent.text = item.name
        if(!item.image.isNullOrBlank()){
            itemView.imgTalent.load(item.image){
                crossfade(true)
                crossfade(20)
                transformations(RoundedCornersTransformation(20f))
            }
        }

    }
}




class Dif : DiffUtil.ItemCallback<TalentLocal>() {
    override fun areItemsTheSame(oldItem: TalentLocal, newItem: TalentLocal): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: TalentLocal, newItem: TalentLocal): Boolean {
        return oldItem == newItem
    }


}

