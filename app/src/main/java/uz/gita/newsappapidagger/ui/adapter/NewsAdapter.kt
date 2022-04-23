package uz.gita.newsappapidagger.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.newsappapidagger.R
import uz.gita.newsappapidagger.data.local.ArticleEntity
import uz.gita.newsappapidagger.databinding.ItemNewsBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var list = ArrayList<ArticleEntity>()
    var clickListener: ((link: ArticleEntity) -> Unit)? = null

    fun setMyClickListener(click: (ArticleEntity) -> Unit) {
        clickListener = click
    }

    fun loadList(contacts: List<ArticleEntity>) {
        list.clear()
        list.addAll(contacts)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            with(list[absoluteAdapterPosition]) {
                binding.title.text = title
                binding.author.text = author
                Glide.with(binding.root)
                    .load(image)
                    .placeholder(R.drawable.img)
                    .into(binding.image)
                binding.description.text = description
                binding.time.text = timestamp
                binding.inShort.text = "short Link"
                binding.readMore.setOnClickListener { clickListener!!.invoke(this) }
            }

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = list.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

}