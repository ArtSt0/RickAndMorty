package com.example.rickandmorty.paging

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.DetailPersonActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.RecyclerViewPersonBinding
import com.example.rickandmorty.retrofit.OnItemClickPerson
import com.example.rickandmorty.retrofit.models.PersonModel
import com.squareup.picasso.Picasso

class PersonRecyclerViewAdapter:
    PagingDataAdapter<PersonModel, PersonRecyclerViewAdapter.PersonHolder>(PersonHolder.REPO_COMPARATOR){

    class PersonHolder(private val binding: RecyclerViewPersonBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(holder: PersonHolder, item: PersonModel?) {
            item?.let {
                binding.tvName.text = it.name
                binding.tvGender.text = it.gender
                binding.tvSpecial.text = it.species
                Picasso.with(holder.itemView.context).load(it.image).into(binding.ivAvatar)
            }

            binding.persons = item
            binding.personClick = object : OnItemClickPerson {
                override fun onItemClickPerson(personModel: PersonModel) {

                    val intent = Intent(binding.root.context, DetailPersonActivity::class.java)
                    intent.putExtra("Person",item)
                    binding.root.context.startActivity(intent)

                }

            }
        }

        companion object{
            val REPO_COMPARATOR = object : DiffUtil.ItemCallback<PersonModel>(){
                override fun areItemsTheSame(oldItem: PersonModel, newItem: PersonModel): Boolean {
                    return oldItem.id==newItem.id
                }

                override fun areContentsTheSame(oldItem: PersonModel, newItem: PersonModel): Boolean {
                    return oldItem==newItem
                }
            }

            fun from(parent: ViewGroup): PersonHolder {
                val binding = DataBindingUtil.inflate<RecyclerViewPersonBinding>(
                    LayoutInflater.from(parent.context), R.layout.recycler_view_person, parent, false)
                return PersonHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        holder.bind(holder, getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        return PersonHolder.from(parent)
    }
}
