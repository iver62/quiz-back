package org.sid.business;

import org.sid.entities.Player;
import org.sid.entities.Question;
import org.sid.repository.PlayerRepository;
import org.sid.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        return this.playerRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Player> getPlayers(final Pageable pageable) {
        return this.playerRepository.findAll(pageable);
    }

    @Override
    public Page<Question> getQuestions(Long id, Pageable pageable) {
        return this.questionRepository.findByPlayer(id, pageable);
    }

    @Override
    public Player createPlayer(final Player player) {
        player.setPassword(this.passwordEncoder.encode(player.getPassword()));
        return this.playerRepository.save(player);
    }

    @Override
    public Player updatePlayer(final Long id, final Player player) {
        player.setId(id);
        player.setPassword(this.passwordEncoder.encode(player.getPassword()));
        return playerRepository.save(player);
    }

    @Override
    public void deletePlayer(final Long id) {
        this.playerRepository.deleteById(id);
    }

}
