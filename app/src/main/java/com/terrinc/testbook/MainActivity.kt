package com.terrinc.testbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.terrinc.testbook.core.TestBookApp
import com.terrinc.testbook.presentation.BibleAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = (application as TestBookApp).mainViewModel

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = BibleAdapter(object : BibleAdapter.Retry {
            override fun tryAgain() {
                viewModel.fetchBooks()
            }
        })
        with(recyclerView){
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }


        viewModel.observe(this) { books ->
            adapter.update(books)
        }
        viewModel.fetchBooks()
    }
}
