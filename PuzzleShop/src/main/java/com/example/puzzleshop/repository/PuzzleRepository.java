package com.example.puzzleshop.repository;

import com.example.puzzleshop.model.entity.Puzzle;
import com.example.puzzleshop.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuzzleRepository extends JpaRepository<Puzzle, String> {

    @Query("SELECT SUM(p.price) FROM Puzzle p")
    Integer findAllByTotalSum();

    List<Puzzle> findAllByCategory_Name(CategoryNameEnum category_name);
}
