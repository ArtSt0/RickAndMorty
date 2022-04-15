package com.example.rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.paging.PersonRecyclerViewAdapter
import com.example.rickandmorty.viewModels.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var vmMain: MainViewModel

    private lateinit var pagingAdapter: PersonRecyclerViewAdapter
    lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).appComponent.inject(this)
        vmMain = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bind.vmMain = vmMain
        bind.lifecycleOwner = this

        bind.rvPersons.layoutManager = LinearLayoutManager(this)
        pagingAdapter = PersonRecyclerViewAdapter()
        pagingAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
        bind.rvPersons.adapter = pagingAdapter
        //bind.rvPersons.adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW


        lifecycleScope.launch {
            vmMain.getCharactersFlow().collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }
}
