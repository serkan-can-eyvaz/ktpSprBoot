package com.example.staj1gun.DAO;

import com.example.staj1gun.Entity.Yazar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IYazarDAO  extends JpaRepository<Yazar, Integer> {
}
