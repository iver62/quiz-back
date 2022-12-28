package org.sid.business;

import org.sid.domain.entities.Player;
import org.sid.domain.entities.Question;
import org.sid.repository.PlayerRepository;
import org.sid.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PlayerServiceImpl extends AbstractServiceImpl<Player> implements PlayerService {

    private final PlayerRepository playerRepository;
    private final QuestionRepository questionRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, QuestionRepository questionRepository, PasswordEncoder passwordEncoder) {
        this.playerRepository = playerRepository;
        this.questionRepository = questionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<Question> getQuestions(final Long idPlayer, final Pageable pageable) {
        return questionRepository.findByPlayerId(idPlayer, pageable);
    }

    @Override
    public Player create(final Player player) {
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        player.setInscription(new Date());
        player.setLastUpdate(new Date());
        return playerRepository.save(player);
    }

    @Override
    public Player update(final Long id, final Player player) {
        player.setId(id);
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        player.setLastUpdate(new Date());
        return playerRepository.save(player);
    }

    @Override
    protected PagingAndSortingRepository<Player, Long> getRepository() {
        return playerRepository;
    }

}
