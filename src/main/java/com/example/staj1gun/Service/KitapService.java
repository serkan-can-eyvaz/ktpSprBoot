package com.example.staj1gun.Service;

import com.example.staj1gun.DAO.IKitapDAO;
import com.example.staj1gun.Entity.Kitap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitapService implements IKitapService {

    private final IKitapDAO kitapDAO;

    public KitapService(IKitapDAO kitapDAO) {
        this.kitapDAO = kitapDAO;
    }


    @Override
    public Kitap createKitap(Kitap request) {
        Kitap kitap1 = new Kitap();
        kitap1.setTitle(request.getTitle());
        return kitapDAO.save(kitap1);
    }

    @Override
    public List<Kitap> getAllKitap() {
        return kitapDAO.findAll();
    }
}
