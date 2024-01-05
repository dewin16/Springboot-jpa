package com.carlos.curso.springboot.jpa.springbootjpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="personas")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    private String name;
    private String secondname;

    //Por defecto se utiliza el nombre de id como nombre de columna, pero al ser dos palabras especificamos el nombre para que no de error (por el tema de "_")
    @Column (name = "programming_language")
    private String programminglanguage;

    //jpa utiliza el constructor vacio para poblar los datos de la tabla
    public Person(){

    }
    //funcionaria como registro
    //Si se añade el constructor es necesario tener un constructor vacio para la persistencia, se puede dejar sin ninguno de los dos contructores

    public Person(Long id, String name, String secondname, String programminglanguage) {
        this.id = id;
        this.name = name;
        this.secondname = secondname;
        this.programminglanguage = programminglanguage;
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
    public String secondname() {
        return secondname;
    }
    public void setLastname(String secondname) {
        this.secondname = secondname;
    }
    public String getprogramminglanguage() {
        return programminglanguage;
    }
    public void setprogramminglanguage(String programminglanguage) {
        this.programminglanguage = programminglanguage;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + ", secondname=" + secondname + ", programminglanguage="
                + programminglanguage + "]";
    }  
    
    
}
