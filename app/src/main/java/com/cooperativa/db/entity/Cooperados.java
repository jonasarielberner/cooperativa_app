package com.cooperativa.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.provider.MediaStore;

import java.util.Date;

@Entity(tableName = "cooperados")
public class Cooperados {
    public Cooperados() {
    }

    @PrimaryKey(autoGenerate = true)
    private Long id;

    /*@ColumnInfo(name = "date")
    private Date creationDate;*/

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "role")
    private String role;

   /* @ColumnInfo(name = "photo")
    private MediaStore.Images.Media photo;*/

    public Cooperados(Long id,
                      Date creationDate,
                      String name,
                      String role,
                      MediaStore.Images.Media photo) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    @Override
    public String toString() {
        return "Cooperados{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
