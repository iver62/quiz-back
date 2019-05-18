package org.sid.business;

import org.sid.domain.entities.Player;
import org.sid.domain.entities.Question;
import org.sid.repository.PlayerRepository;
import org.sid.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Player getPlayer(final Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Player> getPlayers(final Pageable pageable) {
        return playerRepository.findAll(pageable);
    }

    @Override
    public Page<Question> getQuestions(Long idPlayer, Pageable pageable) {
        return questionRepository.findByPlayerId(idPlayer, pageable);
    }

    @Override
    public Player createPlayer(final Player player) {
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        player.setInscription(new Date());
        player.setLastUpdate(new Date());
        return playerRepository.save(player);
    }

    @Override
    public Player updatePlayer(final Long id, final Player player) {
        player.setId(id);
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        return playerRepository.save(player);
    }

    @Override
    public void deletePlayer(final Long id) {
        playerRepository.deleteById(id);
    }

}
