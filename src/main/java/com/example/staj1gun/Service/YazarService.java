package com.example.staj1gun.Service;

import com.example.staj1gun.DAO.IYazarDAO;
import com.example.staj1gun.Entity.Yazar;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class YazarService implements IYazarService {

    private final IYazarDAO yazarDAO;

    public YazarService(IYazarDAO yazarDAO) {
        this.yazarDAO = yazarDAO;
    }

    @Override
    public List<Yazar> yazargetAll() {
        return yazarDAO.findAll();
    }

    @Override
    public Yazar createYazar(Yazar request) {
        Yazar yazar = new Yazar();
        yazar.setName(request.getName());
        yazar.setSurname(request.getSurname());
        return yazarDAO.save(yazar);
    }

}
