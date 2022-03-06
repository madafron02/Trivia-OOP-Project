package server.database;

import org.springframework.data.jpa.repository.JpaRepository;

import commons.Round;

public interface RoundRepository extends JpaRepository<Round, Long> {}
