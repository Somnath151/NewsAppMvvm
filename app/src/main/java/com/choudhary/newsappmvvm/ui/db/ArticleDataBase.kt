package com.choudhary.newsappmvvm.ui.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.choudhary.newsappmvvm.ui.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ArticleDataBase  : RoomDatabase() {

    abstract  fun getArticlesDao(): ArticleDao

    companion object{
        @Volatile
        private  var INSTANCE : ArticleDataBase ? = null
        private  val LOCK = Any()

        operator  fun invoke(context : Context)  = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: createDatabase(context).also{ INSTANCE = it}
        }

        private  fun createDatabase(context: Context ) =

            Room.databaseBuilder(
                context.applicationContext,
                ArticleDataBase::class.java,
                "article_db.db"
            ).build()
    }
}