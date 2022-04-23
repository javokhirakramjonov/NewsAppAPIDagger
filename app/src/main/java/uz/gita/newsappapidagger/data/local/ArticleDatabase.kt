package uz.gita.newsappapidagger.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ArticleEntity::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao

    companion object {
        private lateinit var instance: ArticleDatabase

        fun getDatabase(context: Context): ArticleDatabase {
            if(::instance.isInitialized)
                return instance

            instance = Room.databaseBuilder(context, ArticleDatabase::class.java, "database").build()
            return instance
        }
    }
}