package org.sid.business;

import org.sid.domain.entities.Player;
import org.sid.domain.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerService {

    Player getPlayer(final Long id);

    Page<Player> getPlayers(final Pageable pageable);

    Page<Question> getQuestions(final Long idPlayer, final Pageable pageable);

    Player createPlayer(final Player player);

    Player updatePlayer(final Long id, final Player player);

    void deletePlayer(final Long id);
}
