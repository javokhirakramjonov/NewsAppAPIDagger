package uz.gita.newsappapidagger.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.newsappapidagger.data.model.ItemModel
import uz.gita.newsappapidagger.databinding.ItemNewsBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var list = ArrayList<ItemModel>()
    var clickListener: ((link: ItemModel) -> Unit)? = null

    fun setMyClickListener(click: (ItemModel) -> Unit) {
        clickListener = click
    }

    fun loadList(contacts: List<ItemModel>) {
        list.clear()
        list.addAll(contacts)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
//        private val titleV = itemView.findViewById<TextView>(R.id.title)
//        private val authorV = itemView.findViewById<TextView>(R.id.author)
//        private val imageV = itemView.findViewById<ImageView>(R.id.image)
//        private val descriptionV = itemView.findViewById<TextView>(R.id.description)
//        private val timeV = itemView.findViewById<TextView>(R.id.time)
//        private val inShortV = itemView.findViewById<AppCompatButton>(R.id.inShort)
//        private val readMoreV = itemView.findViewById<AppCompatButton>(R.id.readMore)

        fun bind() {
            with(list[absoluteAdapterPosition]) {
                binding.title.text = title
                binding.author.text = author
                Glide.with(binding.root)
                    .load(image)
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