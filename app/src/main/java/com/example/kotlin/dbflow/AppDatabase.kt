package mobi.porquenao.poc.kotlin.core

import com.raizlabs.android.dbflow.annotation.Database

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION, generatedClassSeparator = "_")
object AppDatabase {
    const val NAME: String = "dbflow_db"
    const val VERSION: Int = 1
}
