package org.ijovic.demo.neo4j.app.config;

import org.ijovic.demo.neo4j.domain.Gender;
import org.ijovic.demo.neo4j.entities.PersonEntity;
import org.ijovic.demo.neo4j.repository.PersonRepository;
import org.neo4j.ogm.session.Neo4jSession;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan({ "org.ijovic.demo.neo4j", "BOOT-INF.org.ijovic.demo.neo4j" })
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = "org.ijovic.demo.neo4j.repository")
public class SpringBootNeo4jPlayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootNeo4jPlayApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(PersonRepository personRepository)  {
		return args -> {
			final String SIBL_REL = "SIBLING";
			final String PAR_REL = "PARENT";

			personRepository.deleteAll();

			PersonEntity ivan = new PersonEntity("Sib1", "Lastname1", "MALE");
			PersonEntity david = new PersonEntity("Sib2", "Lastname1", "MALE");

			PersonEntity mama = new PersonEntity("Mama", "Lastname1", "FEMALE");
			PersonEntity dad = new PersonEntity("Father", "Lastname1", "MALE");

			personRepository.save(ivan);
			personRepository.save(dad);
			personRepository.save(mama);
			personRepository.save(david);

			ivan.createRelation(david, SIBL_REL);
			ivan.createRelation(mama, PAR_REL);
			ivan.createRelation(dad, PAR_REL);

			personRepository.save(ivan);

			david.createRelation(mama, PAR_REL);
			david.createRelation(dad, PAR_REL);

			personRepository.save(david);

		};

	}
}
