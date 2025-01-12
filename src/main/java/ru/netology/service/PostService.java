package ru.netology.service;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
  private final PostRepository repository;

  public PostService() {
    this.repository = new PostRepository();
  }

  public List<Post> all() {
    return repository.all();
  }

  public Post getById(long id) {
    return repository.getById(id).orElseThrow(() -> new NotFoundException("Пост с id " + id + " не найден."));
  }

  public Post save(Post post) {
    return repository.save(post);
  }

  public void removeById(long id) {
    if (!repository.getById(id).isPresent()) {
      throw new NotFoundException("Пост с id " + id + " не найден.");
    }
    repository.removeById(id);
  }
}

