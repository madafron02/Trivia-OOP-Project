package server.database;

import commons.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Quote, Long> {}

