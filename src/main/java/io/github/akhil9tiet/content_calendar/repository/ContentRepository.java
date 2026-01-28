package io.github.akhil9tiet.content_calendar.repository;

import io.github.akhil9tiet.content_calendar.model.Content;
import org.springframework.data.repository.ListCrudRepository;

public interface ContentRepository extends ListCrudRepository<Content,Integer> {
}
