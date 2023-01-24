package com.akshay.mindvalley.home.data.db.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.akshay.mindvalley.home.data.local.db.MindValleyDatabase
import com.akshay.mindvalley.home.data.local.db.dao.CategoryDao
import com.akshay.mindvalley.home.data.local.model.CategoryEntity
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryDaoTest : TestCase(){

    private lateinit var db: MindValleyDatabase
    private lateinit var dao: CategoryDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MindValleyDatabase::class.java).build()
        dao = db.categoryDao()
    }
    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadCategory() = runBlocking {
        val category = listOf<CategoryEntity>(
            CategoryEntity(name ="Health" ),
            CategoryEntity(name ="Social" )
        )

        dao.insert(category)
        val categoryList = dao.loadCategoryList()
        assertEquals(categoryList.map { it.name }, category.map { it.name })
        assertEquals(categoryList.mapNotNull { it.id }.size , category.size)
    }

    @Test
    fun writeDeleteReadCategory() = runBlocking {
        val category = listOf<CategoryEntity>(
            CategoryEntity(name ="Health" ),
            CategoryEntity(name ="Social" )
        )

        dao.insert(category)
        dao.deleteAll()
        val categoryList = dao.loadCategoryList()
        assertTrue(categoryList.isEmpty())
    }

}