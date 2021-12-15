package com.example.repository

import com.example.models.ApiResponse
import com.example.models.Hero
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

class HeroRepositoryImpl: HeroRepository {

    override val heroes: Map<Int, List<Hero>> by lazy {
        mapOf(
            1 to page1,
            2 to page2,
            3 to page3,
            4 to page4,
            5 to page5
        )
    }

    val client = KMongo.createClient()
    val database = client.getDatabase("heroes")
    val col = database.getCollection<Hero>()


    override val page1 = col.find().limit(3).toList()
    override val page2 = col.find().skip(3).limit(3).toList()
    override val page3 = col.find().skip(6).limit(3).toList()
    override val page4 = col.find().skip(9).limit(3).toList()
    override val page5 = col.find().skip(12).limit(3).toList()

    override suspend fun getAllHeroes(page: Int): ApiResponse {

        return ApiResponse(
            success = true,
            message = "ok",
            prevPage = calculatePage(page)["prevPage"],
            nextPage = calculatePage(page)["nextPage"],
            heroes = heroes[page]!!
        )
    }

    private fun calculatePage(page: Int): Map<String, Int?>{
        var prevPage: Int? = page
        var nextPage: Int? = page
        if (page in 1..4){
            nextPage = nextPage?.plus(1)
        }
        if(page in 2..5){
            prevPage = prevPage?.minus(1)
        }
        if(page == 1){
            prevPage = null
        }

        if(page == 5){
            nextPage = null
        }

        return mapOf("prevPage" to prevPage, "nextPage" to nextPage)
    }

    override suspend fun searchHeroes(name: String): ApiResponse {
        TODO("Not yet implemented")
    }
}