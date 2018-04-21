package com.newsagency.services;

import com.newsagency.entities.Writer;
import com.newsagency.repositories.WriterRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class WriterService {

    @Inject
    WriterRepository writerRepository;

    public List<Writer> getAll()
    {
        return writerRepository.findAll();
    }

    public Writer getById(int id)
    {
        return writerRepository.findByWriterid(id);
    }

    public Writer getByName(String name)
    {
        return writerRepository.findByName(name);
    }

    public Writer getByUsername(String username)
    {
        return writerRepository.findByUsername(username);
    }

    public Writer insert(Writer writer)
    {
        return writerRepository.save(writer);
    }

    public void delete(Writer writer)
    {
        writerRepository.delete(writer);
    }

}
