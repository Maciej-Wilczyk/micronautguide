package example.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import java.time.Month;
import java.util.List;

@Controller // <1>
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @Get("/{month}")
    public News index(Month month) {
        return new News(month, newsService.headlines(month));
    }

    @Put(
            consumes = MediaType.APPLICATION_JSON,
            processes = MediaType.APPLICATION_JSON
    )
    public List<String> update(@Body News news) {
        return newsService.addHeadline(news.getMonth(), news.getHeadlines());
    }

    @Delete(
            consumes = MediaType.APPLICATION_JSON,
            processes = MediaType.APPLICATION_JSON
    )
    public void remove(@Body News news) {
         newsService.removeHeadline(news.getMonth(), news.getHeadlines());
    }
}
