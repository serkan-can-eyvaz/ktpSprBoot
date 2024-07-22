package com.example.staj1gun.Service;

import com.example.staj1gun.Entity.Yazar;

import java.util.List;

public interface IYazarService {
    List<Yazar> yazargetAll();
    Yazar createYazar(Yazar yazar);
}
