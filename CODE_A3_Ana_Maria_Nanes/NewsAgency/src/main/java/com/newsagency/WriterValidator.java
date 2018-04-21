package com.newsagency;

import com.newsagency.entities.Writer;
import com.newsagency.services.WriterService;

import javax.inject.Inject;
import java.util.List;

public class WriterValidator {

    @Inject
    WriterService writerService;

    public WriterValidator() {
    }

    public boolean validateWriter(Writer wr)
    {
        if(isUsernameUnique(wr.getUsername())==false)
            return false;
        if(wr.getName().equals(""))
            return false;
        if(wr.getUsername().equals(""))
            return false;
        if(wr.getPassword().equals(""))
            return false;
        return true;
    }

    private boolean isUsernameUnique(String username) {
        List<Writer> allWriters = writerService.getAll();
        for(Writer wr: allWriters)
        {
            if(wr.getUsername().equals(username))
            {
                return false;
            }
        }
        return true;
    }
}
