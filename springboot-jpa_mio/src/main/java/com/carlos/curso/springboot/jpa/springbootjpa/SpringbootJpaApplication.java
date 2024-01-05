package com.carlos.curso.springboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.curso.springboot.jpa.springbootjpa.entities.Person;
import com.carlos.curso.springboot.jpa.springbootjpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		create();
	}

	@Transactional
	public void delete(){
		repository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingresa el ID a eliminar");
		Long id = scanner.nextLong();
		repository.deleteById(id);

		repository.findAll().forEach(System.out::println);

		scanner.close();

	}

	@Transactional
	public void update(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingresa el ID");
		Long id = scanner.nextLong();
		Optional<Person> optinalPerson = repository.findById(id);

		optinalPerson.ifPresent(person -> {
			System.out.println(person);
			System.out.println("Ingrese el lenguaje de programacion: ");
			String programminglanguage = scanner.next();
			person.setprogramminglanguage(programminglanguage);
			Person persondb = repository.save(person);
			System.out.println(persondb);
		});

		scanner.close();
	}	

	@Transactional
	public void create(){
		/*Scanner scanner = new Scanner(System.in);
		System.out.println("Ingresa el nombre");
		String name = scanner.next();
		System.out.println("Ingresa el apellido");
		String secondname = scanner.next();
		System.out.println("Ingresa el lenguage de programacion");
		String programminglanguage = scanner.next();
		scanner.close();
		*/Person person = new Person(null,"Lalo","Thor","Phyton");
		//Person person = new Person(null, name, secondname, programminglanguage);
		Person personNew = repository.save(person);
		System.out.println(personNew);

		//repository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	//readonly = true si no modifica la base de datos solo la lee
	@Transactional(readOnly = true)
	public void list(){

	//carga la tabla
		//List<Person> persons = (List<Person>) repository.findByProgramminglanguage("Java");
		List<Person> persons = (List<Person>) repository.buscarProgramminglanguage("Python", "Pepe");


		persons.stream().forEach(person->{
			System.out.println(person);
		});
		
		List<Object[]> personvalues = repository.obtenerPersonData();
			personvalues.stream().forEach(person->{
			System.out.println(person[0] + " es experto en " + person[1]);
		});

	}

	@Transactional(readOnly = true)
	public void findOne(){
	/*Person person = null;
	
	Optional<Person> optionalPerson = repository.findById(1L);
	if(optionalPerson.isPresent()){
		person = optionalPerson.get();
	} 
	System.out.println(person);

	//Esta linea de codigo equivale a las de arriba
	//repository.findById(1L).ifPresent(person -> System.out.println(person));
	//repository.findOne(1L).ifPresent(person -> System.out.println(person)); //Esta seria con la query personalizada
*/
	repository.findOneLikeName("Pe").ifPresent(System.out::println);
}

}
