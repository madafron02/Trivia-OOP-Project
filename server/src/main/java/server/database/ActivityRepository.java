package server.database;

import commons.Activity;
import commons.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {}

