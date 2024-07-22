package com.example.staj1gun.DAO;

import com.example.staj1gun.Entity.Yazar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IYazarDAO  extends JpaRepository<Yazar, Integer> {
}
