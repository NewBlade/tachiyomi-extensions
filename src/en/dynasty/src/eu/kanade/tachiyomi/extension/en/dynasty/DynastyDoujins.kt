package eu.kanade.tachiyomi.extension.en.dynasty

import eu.kanade.tachiyomi.network.GET
import eu.kanade.tachiyomi.source.model.FilterList
import eu.kanade.tachiyomi.source.model.SManga
import okhttp3.Request
import org.jsoup.nodes.Document

class DynastyDoujins : DynastyScans() {

    override val name = "Dynasty-Doujins"

    override fun popularMangaInitialUrl() = "$baseUrl/doujins?view=cover"

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        return GET("$baseUrl/search?q=$query&classes%5B%5D=Doujin&sort=&page=$page", headers)
    }

    override fun mangaDetailsParse(document: Document): SManga {
        val manga = SManga.create()
        super.mangaDetailsParse(document)
        parseThumbnail(manga)
        manga.author = ".."
        manga.status = SManga.UNKNOWN
        parseGenres(document, manga)
        return manga
    }

    override fun chapterListSelector() = "div.span9 > dl.chapter-list > dd"

}
