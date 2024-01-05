package com.carlos.curso.springboot.jpa.springbootjpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.carlos.curso.springboot.jpa.springbootjpa.entities.Person;

//asociamos el repositorio a la tabla <Nombretabla, tipodevalordelaclaveprimaria>
public interface PersonRepository extends CrudRepository<Person, Long> {

    //Utilizar optional son buenas practicas porque envuelve al objeto para saber si este esta presente

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    //like hace que busque en base a las coincidencias y los porcentajes indican que busque a ambos lados
     @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);
    //Es la misma busqueda que la de arriba con los metodos default de crudrepository
    Optional<Person> findByNameContaining(String name);
    
    //Se crea un metodo de busqueda
    List<Person> findByProgramminglanguage(String programminglanguage);

    //Busqueda personalizada
    @Query("select p from Person p where p.programminglanguage=?1 and p.name=?2")
    List<Person> buscarProgramminglanguage(String programminglanguage, String name);

    //Es lo mismo que el anterior usando metodos predeterminados de CrudRepository
    List<Person> findByProgramminglanguageAndName(String programminglanguage, String name);

    @Query("select p.name, p.programminglanguage from Person p")
    List<Object[]> obtenerPersonData();


} 



