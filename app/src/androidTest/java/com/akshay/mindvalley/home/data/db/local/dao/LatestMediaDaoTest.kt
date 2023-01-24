package com.akshay.mindvalley.home.data.db.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.akshay.mindvalley.home.data.local.db.MindValleyDatabase
import com.akshay.mindvalley.home.data.local.db.dao.LatestMediaDao
import com.akshay.mindvalley.home.data.local.model.LatestMediaEntity
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LatestMediaDaoTest : TestCase(){

    private lateinit var db: MindValleyDatabase
    private lateinit var dao: LatestMediaDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MindValleyDatabase::class.java).build()
        dao = db.latestMediaDao()
    }
    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadCategory() = runBlocking {
        val testData = listOf<LatestMediaEntity>(
            LatestMediaEntity(
                type = "course",
                title ="Conscious Parenting",
                coverAsset = "",
                channel="Little Humans"
            )
        )

        dao.insert(testData)
        val latestMediaEntityList = dao.loadLatestMediaList()

        assertEquals(latestMediaEntityList, testData)
    }

    @Test
    fun writeDeleteReadCategory() = runBlocking {
        val testData = listOf<LatestMediaEntity>(
            LatestMediaEntity(
                type = "course",
                title ="Conscious Parenting",
                coverAsset = "",
                channel="Little Humans"
            )
        )

        dao.insert(testData)
        dao.deleteAll()
        val latestMediaEntityList = dao.loadLatestMediaList()

        assertTrue(latestMediaEntityList.isEmpty())
    }

}