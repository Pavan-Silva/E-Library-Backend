package lk.elib.elibrarybackend.repository;

import lk.elib.elibrarybackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByNameIgnoreCase(String category);
}