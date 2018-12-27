package org.sid.business;

import org.sid.entities.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerService {

    Player getPlayer(final Long id);

    Page<Player> getPlayers(final Pageable pageable);

    Player createPlayer(final Player player);

    Player updatePlayer(final Long id, final Player player);

    void deletePlayer(final Long id);
}
