package com.example.staj1gun.Entity;

import jakarta.persistence.*;

@Entity
public class Kitap
{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "Yazar_id")
    private Yazar yazar;
    public Kitap() {
    }

    public Kitap(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
