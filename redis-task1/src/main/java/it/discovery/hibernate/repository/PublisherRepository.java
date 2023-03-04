package it.discovery.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.discovery.hibernate.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

}
