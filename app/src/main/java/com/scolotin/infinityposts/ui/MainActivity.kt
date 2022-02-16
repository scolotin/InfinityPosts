package com.scolotin.infinityposts.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scolotin.infinityposts.R
import com.scolotin.infinityposts.databinding.ActivityMainBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: PostAdapter
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    private var disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        adapter = PostAdapter()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            postList.layoutManager = layoutManager
            postList.adapter = adapter

            val decoration = DividerItemDecoration(applicationContext, RecyclerView.VERTICAL)
            val dividerDrawable = ResourcesCompat.getDrawable(resources, R.drawable.divider, theme)
            dividerDrawable?.let {
                decoration.setDrawable(it)
                postList.addItemDecoration(decoration)
            }
        }

        disposables.add(
            mainViewModel
                .getPosts()
                .subscribeOn(Schedulers.io())
                .subscribe {
                    adapter.submitData(lifecycle, it)
                }
        )

    }

    override fun onDestroy() {
        super.onDestroy()

        disposables.dispose()
    }
}