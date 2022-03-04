package server.database;

import commons.LeaderBoardEntry;
import commons.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderBoardEntryRepository extends JpaRepository<LeaderBoardEntry, Player> {
}
