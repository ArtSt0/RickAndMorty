package com.example.rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.databinding.ActivityDetailPersonBinding
import com.example.rickandmorty.retrofit.models.PersonModel
import com.example.rickandmorty.viewModels.DetailViewModel
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailPersonActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var vmDetail: DetailViewModel

    lateinit var bind: ActivityDetailPersonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).appComponent.inject(this)
        vmDetail = ViewModelProvider(this, factory).get(DetailViewModel::class.java)

        bind = DataBindingUtil.setContentView(this, R.layout.activity_detail_person)
        bind.vmDetail = vmDetail
        bind.lifecycleOwner = this

        val person = intent.getSerializableExtra("Person") as PersonModel
        person.let {
            title = it.name + " Detail"
            bind.tvName.text = it.name
            bind.tvGender.text = it.gender
            bind.tvSpecial.text = it.species
            bind.tvStatus.text = it.status
            Picasso.with(this).load(it.image).into(bind.ivAvatar)
            bind.tvLocation.text = it.location?.name
            bind.tvEpisode.text = it.episode?.size.toString()
        }
    }
}
