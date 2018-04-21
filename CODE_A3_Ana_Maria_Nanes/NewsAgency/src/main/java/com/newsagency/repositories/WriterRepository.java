package com.newsagency.repositories;

import com.newsagency.entities.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WriterRepository extends JpaRepository<Writer, Integer> {

    public List<Writer> findAll();

    public Writer findByWriterid(int id);

    public Writer findByName(String name);

    public Writer findByUsername(String username);

    public Writer save(Writer writer);

    public void delete(Writer writer);


}
