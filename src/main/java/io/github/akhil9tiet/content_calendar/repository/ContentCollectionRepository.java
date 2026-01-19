package io.github.akhil9tiet.content_calendar.repository;

import io.github.akhil9tiet.content_calendar.model.Content;
import io.github.akhil9tiet.content_calendar.model.Status;
import io.github.akhil9tiet.content_calendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository(){

    }

    public List<Content> findAll(){
        return contentList;
    }

    public Optional<Content> findById(Integer id){
        return contentList.stream().filter(c->c.id().equals(id)).findFirst();
    }

    public void save(Content content){
        contentList.removeIf(c->c.id().equals(content.id()));
        contentList.add(content);
    }

    public boolean existsById(Integer id) {
        return contentList.stream().anyMatch(c->c.id().equals(id));
    }

    @PostConstruct
    private void initData(){
        contentList.add(new Content(1, "First Content", "This is the first content", Status.IDEA, Type.ARTICLE, null, null, ""));
        contentList.add(new Content(2, "Second Content", "This is the second content", Status.IN_PROGRESS, Type.VIDEO, null, null, null));
        contentList.add(new Content(3, "Third Content", "This is the third content", Status.PUBLISHED, Type.PODCAST, null, null, null));
    }


    public void delete(Integer id) {
        contentList.removeIf(c->c.id().equals(id));
    }
}
