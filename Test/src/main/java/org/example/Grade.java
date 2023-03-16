package org.example;

public class Grade {

    private int ordreHierarchique;
    private String libelle;
    private int salary;

    /**
     * Constructor
     */
    public Grade(int ordreHierarchique, String libelle, int salary) {
        super();
        this.ordreHierarchique = ordreHierarchique;
        this.libelle = libelle;
        this.salary = salary;
    }

    /** Getters & Setters **/
    public int getOrdreHierarchique() {
        return ordreHierarchique;
    }
    public void setOrdreHierarchique(int ordreHierarchique) {
        this.ordreHierarchique = ordreHierarchique;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public int getSalary() {
        return salary>10000?10000 : salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }


}






////////////////////////////////////////////////

package org.sid.springmvc;

import java.time.LocalDate;
import java.util.Date;

import org.sid.springmvc.dao.PatientRepository;
import org.sid.springmvc.enteties.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringmvcApplication implements CommandLineRunner {
	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringmvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		patientRepository.save(new Patient(null, "Med", LocalDate.now(), false,5));
		patientRepository.save(new Patient(null, "mohamed", LocalDate.now(), false,3));
		patientRepository.save(new Patient(null, "ahmed", LocalDate.now(), false,5));
		patientRepository.save(new Patient(null, "Alpha", LocalDate.now(), false,3));
		patientRepository.save(new Patient(null, "Beta", LocalDate.now(), false,3));
		patientRepository.save(new Patient(null, "toto", LocalDate.now(), false,5));
		patientRepository.save(new Patient(null, "Achraf", LocalDate.now(), false,6));
		patientRepository.save(new Patient(null, "Jack", LocalDate.now(), false,3));
		patientRepository.save(new Patient(null, "Nicole", LocalDate.now(), false,5));
		patientRepository.save(new Patient(null, "Med", LocalDate.now(), false,4));
		patientRepository.save(new Patient(null, "mohamed", LocalDate.now(), false,3));
		patientRepository.save(new Patient(null, "ahmed", LocalDate.now(), false,5));
		patientRepository.save(new Patient(null, "Med", LocalDate.now(), false,8));
		patientRepository.save(new Patient(null, "mohamed", LocalDate.now(), false,3));
		patientRepository.save(new Patient(null, "ahmed", LocalDate.now(), false,12));


		patientRepository.findAll().forEach(p ->{
			System.out.println(p.getName());
		});
	}

}


package org.sid.springmvc.dao;

import org.sid.springmvc.enteties.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	public Page<Patient> findByNameContains(String mc,Pageable pageable);
}



//////////////////////////////////////////

package org.sid.springmvc.enteties;

import java.time.LocalDate;
import java.util.Date;



import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Patient {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 3,max = 15,message="Nom incorect")
	private String name;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateNaissance;
	private boolean malade;
	@DecimalMin("3")
	private int score;
	
}








//////////////////////










package org.sid.springmvc.web;

import java.time.LocalDate;
import java.util.List;



import org.sid.springmvc.dao.PatientRepository;
import org.sid.springmvc.enteties.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PatientController {
	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping(path="/index")
	public String index() {
		return "index";
	}
	
	@GetMapping(path="/patients")
	public String list(Model model,
			@RequestParam(name="page",defaultValue = "0") int page,
			@RequestParam(name="size",defaultValue = "3")int size,
			@RequestParam(name="keyword",defaultValue = "")String mc) {
		Page<Patient> pagePatients=patientRepository.findByNameContains(mc, PageRequest.of(page, size));
		model.addAttribute("patients",pagePatients.getContent());
		model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("size",size);
		model.addAttribute("keyword",mc);

		return "patients";
	}
	@GetMapping(path="/deletePatient")
	public String delete(Long id,String keyword,int page, int size) {
		patientRepository.deleteById(id);
		return "redirect:/patients?page="+page+"&size="+size+"&keyword="+keyword;
	}
	@GetMapping(path="/deletePatient2")
	public String delete2(Long id,String keyword,int page, int size, Model model) {
		patientRepository.deleteById(id);
		return list(model, page, size, keyword);
	}
	
	@GetMapping(path="/formPatient")
	public String formPatient(Model model) {
		model.addAttribute("patient", new Patient());
		model.addAttribute("mode","new");
		return "formPatient";
	}
	
	@PostMapping(path="/savePatient")
	public String savePatient(Model model, @Valid Patient patient,BindingResult bindingResult) {
		if(bindingResult.hasErrors())return"formPatient";
		patientRepository.save(patient);
		model.addAttribute("patient", patient);
		return"confirmation";
	}
	
	@GetMapping(path="/editPatient")
	public String editPatient(Model model, Long id) {
		Patient p=patientRepository.findById(id).get();
		model.addAttribute("patient", p);
		model.addAttribute("mode","edit");
		return "formPatient";
	}

}





////////////////////////  Confirmation


<!DOCTYPE html>
<html xmlns:th="http://www.thymeLeaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	layout:decorate="template1.html">
<head>
<meta charset="utf-8">
<title>Page patients</title>

</head>
<body>


	<div layout:fragment="pageContent">
		<div class="container">
			<div class="card">
				<div class="card-header">Enregistrement effectué</div>
				<div class="card-body"></div>
				<div class="form-group">
					<label>ID : </label> <strong><label
						th:text="${patient.id}"></label></strong>

				</div>
				<div class="form-group">
					<label>Name :</label> <strong><label
						th:text="${patient.name}"></label></strong>

				</div>
				<div class="form-group">
					<label>Date de naissance : </label> <strong><label
						th:text="${patient.dateNaissance}"></label></strong>

				</div>
				<div class="form-group">
					<label>Score : </label> <strong><label
						th:text="${patient.score}"></label></strong>

				</div>
				<div class="form-group">
					<label>Malade : </label> <strong><label
						th:text="${patient.malade}"></label></strong>

				</div>
			</div>

		</div>
	</div>

</body>
</html>


//////////////////// formPatient


<!DOCTYPE html>
<html xmlns:th="http://www.thymeLeaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	layout:decorate="template1.html">
<head>
<meta charset="utf-8">
<title>Page patients</title>

</head>
<body>


	<div layout:fragment="pageContent">
		<div class="container mt-5">
			<div class="card">
				<div class="card-header" th:if="${mode=='new'}" >Formulaire de saisie d'un nouveau patient</div>
				<div class="card-header" th:if="${mode=='edit'}" >Formulaire de mise à jour</div>
				<div class="card-body">
					<form th:action="@{savePatient}" method="post">
						<div class="form-group">
							 
							<input type="hidden" name="id" class="form-control" th:value="${patient.id}">
							<span th:errors="${patient.name}" class="text-danger"></span>
						</div>
						<div class="form-group">
							<label class="control-label">Name</label> <input type="text"
								name="name" class="form-control" th:value="${patient.name}">
							<span th:errors="${patient.name}" class="text-danger"></span>
						</div>
						<div class="form-group">
							<label class="control-label">Date Naissance</label> 
							<input type="date" name="dateNaissance" class="form-control" th:value="${patient.dateNaissance}">
							 <span th:errors="${patient.dateNaissance}" class="text-danger"></span>
						</div>
						<div class="form-group">
							<label class="control-label">Score</label> 
							<input type="text" name="score" class="form-control" th:value="${patient.score}">
							<span th:errors="${patient.score}" class="text-danger"></span>
						</div>
						<div class="form-group">
							<label class="control-label">Malade</label>
							 <input type="checkbox" name="malade" th:checked="${patient.malade}">
							<span th:errors="${patient.malade}" class="text-danger"></span>
						</div>
						<button class="btn btn-success" type="submit">Save</button>
					</form>
				</div>
			</div>


		</div>
	</div>

</body>
</html>








///////////////////////  patients

<!DOCTYPE html>
<html xmlns:th="http://www.thymeLeaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	layout:decorate="template1.html">
<head>
<meta charset="utf-8">
<title>Page patients</title>

</head>
<body>
<!-- 	<div class="container mt-5">
		<div class="card">
			<div class="card-header">List des patients</div>
			<div class="card-body">
				<form action="get" th:action="@{patients}">
					<div class="form-group">
						<label>Name</label> <input type="text" name="keyword"
							th:value="${keyword}">

						<button class="btn btn-success">Cherhcer</button>
					</div>
				</form>
				<table class="table">
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Date Naissance</th>
						<th>malade</th>
					</tr>

					<tr th:each="p:${patients}">
						<td th:text="${p.id}"></td>
						<td th:text="${p.name}"></td>
						<td th:text="${p.dateNaissance}"></td>
						<td th:text="${p.malade}"></td>
						<td><a
							onclick="return confirm('Are you sure u want to delete')"
							class="btn btn-danger"
							th:href=@{deletePatient2(id=${p.id},keyword=${keyword},page=${currentPage},size=${size})}>Delete</a>
						</td>
					</tr>
				</table>
				<ul class="nav nav-pills">
					<li th:each="page,status:${pages}"><a
						th:class="${status.index==currentPage?'btn btn-primary': 'btn'}"
						th:href="@{patients(page=${status.index},keyword=${keyword},size=${size})}"
						th:text=${status.index}></a></li>
				</ul>

			</div>
		</div>
	</div> -->
	
	
	<div layout:fragment="pageContent">
	<div class="container mt-5">
		<div class="card">
			<div class="card-header">List des patients</div>
			<div class="card-body">
				<form action="get" th:action="@{patients}">
					<div class="form-group">
						<label>Name</label> <input type="text" name="keyword"
							th:value="${keyword}">

						<button class="btn btn-success">Cherhcer</button>
					</div>
				</form>
				<table class="table">
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Date Naissance</th>
						<th>Malade</th>
						<th>Score</th>
					</tr>

					<tr th:each="p:${patients}">
						<td th:text="${p.id}"></td>
						<td th:text="${p.name}"></td>
						<td th:text="${p.dateNaissance}"></td>
						<td th:text="${p.malade}"></td>
						<td th:text="${p.score}"></td>
						<td><a
							onclick="return confirm('Are you sure u want to delete')"
							class="btn btn-danger"
							th:href=@{deletePatient2(id=${p.id},keyword=${keyword},page=${currentPage},size=${size})}>Delete</a>
						</td>
						<td>
						<a class="btn btn-success" th:href=@{editPatient(id=${p.id})}>Edit</a>
						</td>
					</tr>
				</table>
				<ul class="nav nav-pills">
					<li th:each="page,status:${pages}"><a
						th:class="${status.index==currentPage?'btn btn-primary': 'btn'}"
						th:href="@{patients(page=${status.index},keyword=${keyword},size=${size})}"
						th:text=${status.index}></a></li>
				</ul>

			</div>
		</div>
	</div>
	</div>

</body>
</html>





//////////////////////////////////// template 1
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
<meta charset="utf-8">
<title>Patients</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand -->
  <a class="navbar-brand" th:href="@{patients}">Home</a>

  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="#">Patients</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Medecins</a>
    </li>

    <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        Patients
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" th:href="@{formPatient}">Ajouter</a>
        <a class="dropdown-item" href="#">Chercher</a>
      </div>
    </li>
    </ul>
   <ul class="navbar-nav ml-auto">
      <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
       	[Username]
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="#">Login</a>
        <a class="dropdown-item" href="#">Logout</a>
        <a class="dropdown-item" href="#">Profile</a>
      </div>
    </li>
  </ul>
</nav>

	<section layout:fragment="pageContent"></section>

</body>
</html>






////////////////////////////////










<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.0.4</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>org.sid</groupId>
	<artifactId>springmvc</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>springmvc</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>javax.validation</groupId>
			<artifactId>validation-api</artifactId>
			<version>2.0.1.Final</version>
		</dependency>
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-validator</artifactId>
			<version>7.0.1.Final</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<!--		    <dependency>
      <groupId>com.mysql</groupId>
      <artifactId>mysql-connector-j</artifactId>
      <scope>runtime</scope>
    </dependency>-->
		<dependency>
			<groupId>nz.net.ultraq.thymeleaf</groupId>
			<artifactId>thymeleaf-layout-dialect</artifactId>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>bootstrap</artifactId>
			<version>5.2.3</version>
		</dependency>
	</dependencies>
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>
</project>



