package com.example.staj1gun.Service;

import com.example.staj1gun.Entity.Kitap;

import java.util.List;

public interface   IKitapService {

    Kitap  createKitap(Kitap kitap);

    List<Kitap> getAllKitap();
}
