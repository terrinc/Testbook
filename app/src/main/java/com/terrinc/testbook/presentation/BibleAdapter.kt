package com.terrinc.testbook.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.terrinc.testbook.R

class BibleAdapter(private val retry: Retry) : RecyclerView.Adapter<BibleAdapter.BibleViewHolder>() {

    private val books = ArrayList<BookUi>()

    fun update(new: List<BookUi>) {
        with(books) {
            clear()
            addAll(new)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        BASE -> BibleViewHolder.Base(R.layout.book_item.makeView(parent))
        TESTAMENT -> BibleViewHolder.Base(R.layout.testament_item.makeView(parent))
        FAIL -> BibleViewHolder.FullScreenFail(R.layout.fail_fullscreen.makeView(parent), retry)
        else -> BibleViewHolder.FullScreenProgress(R.layout.progress_fullscreen.makeView(parent))
    }

    override fun getItemViewType(position: Int) = when (books[position]) {
        is BookUi.Testament -> TESTAMENT
        is BookUi.Base -> BASE
        is BookUi.Fail -> FAIL
        is BookUi.Process -> PROGRESS
        else -> -1
    }

    override fun onBindViewHolder(holder: BibleViewHolder, position: Int) =
        holder.bind(books[position])

    override fun getItemCount() = books.size

    abstract class BibleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(book: BookUi) = Unit

        class FullScreenProgress(view: View) : BibleViewHolder(view)

        class FullScreenFail(view: View, private val retry: Retry) : BibleViewHolder(view) {
            private val message = itemView.findViewById<TextView>(R.id.messageTextView)
            private val button = itemView.findViewById<Button>(R.id.tryAgainButton)

            override fun bind(book: BookUi) {
                book.map(object : BookUi.StringMapper {
                    override fun map(text: String) {
                        message.text = text
                    }
                })
                button.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }

        class Base(view: View) : BibleViewHolder(view) {
            private val name = itemView.findViewById<TextView>(R.id.textView)

            override fun bind(book: BookUi) {
                book.map(object : BookUi.StringMapper {
                    override fun map(text: String) {
                        name.text = text
                    }
                })
            }
        }
    }

    interface Retry {
        fun tryAgain()
    }

    companion object {
        private const val BASE = 0
        private const val TESTAMENT = 1
        private const val FAIL = 2
        private const val PROGRESS = 3
    }
}

private fun Int.makeView(parent: ViewGroup) = LayoutInflater.from(parent.context).inflate(this, parent, false)
