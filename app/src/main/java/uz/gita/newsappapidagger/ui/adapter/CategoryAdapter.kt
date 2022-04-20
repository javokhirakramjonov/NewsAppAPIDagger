package uz.gita.newsappapidagger.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.newsappapidagger.databinding.ItemCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var list = arrayListOf(
        "ALL",
        "NATIONAL",
        "BUSINESS",
        "SPORTS",
        "WORLD",
        "POLITICS",
        "TECHNOLOGY",
        "STARTUP",
        "ENTERTAINMENT",
        "SCIENCE",
        "AUTOMOBILE"
    )
    var clickListener: ((name: String) -> Unit)? = null

    fun setMyClickListener(click: (String) -> Unit) {
        clickListener = click
    }

    inner class ViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            with(list[absoluteAdapterPosition]) {
                binding.name.text = this
                binding.clicker.setOnClickListener { clickListener!!.invoke(this) }
            }

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = list.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

}