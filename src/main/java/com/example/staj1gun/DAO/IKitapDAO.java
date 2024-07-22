package com.example.staj1gun.DAO;

import com.example.staj1gun.Entity.Kitap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IKitapDAO extends JpaRepository<Kitap,Integer> {

}
