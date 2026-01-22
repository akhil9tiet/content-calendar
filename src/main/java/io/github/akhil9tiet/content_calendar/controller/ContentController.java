package io.github.akhil9tiet.content_calendar.controller;

import io.github.akhil9tiet.content_calendar.model.Content;
import io.github.akhil9tiet.content_calendar.repository.ContentJdbcTemplateRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {


    //Dependency injections and API endpoints will be added here in the future.
//    private final ContentCollectionRepository repository;
      private final ContentJdbcTemplateRepository repository;

//    public ContentController(ContentCollectionRepository contentCollectionRepository) {
//        this.repository = contentCollectionRepository;
//    }

    public ContentController(ContentJdbcTemplateRepository repository) {
        this.repository = repository;
    }


    // make a request and find all the content
    @GetMapping("")
    public List<Content> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id){
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not Found"));
    }

    @PostMapping("")
    public void create(@Valid @RequestBody Content content){
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Content content, Integer id){
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not Found");
        }
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not Found");
        }
        repository.findById(id).ifPresent(content -> {
            repository.delete(id);
        });
    }

}
