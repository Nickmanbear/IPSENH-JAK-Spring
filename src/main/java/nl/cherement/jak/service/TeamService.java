package nl.cherement.jak.service;

import nl.cherement.jak.entity.TeamEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.TeamRepository;
import nl.cherement.jak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService extends AbstractService<TeamEntity> {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    public TeamService(TeamRepository repository) {
        super(repository);
    }

    public List<TeamEntity> findByMember(String memberName) {
        UserEntity userEntity = userRepository.findByUsername(memberName);

        return teamRepository.findByMembers(userEntity);
    }

    public TeamEntity saveNew(String teamName, Principal principal) {
        UserEntity userEntity = userRepository.findByUsername(principal.getName());
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.members = new ArrayList<>();
        teamEntity.id = 0;
        teamEntity.name = teamName;
        teamEntity.leader = userEntity;
        teamEntity.members.add(userEntity);

        return teamRepository.save(teamEntity);
    }

    public TeamEntity addMember(Long teamId, Long memberId) {
        Optional<TeamEntity> teamEntity = teamRepository.findById(teamId);
        Optional<UserEntity> userEntity = userRepository.findById(memberId);
        if (teamEntity.isPresent() && userEntity.isPresent()) {
            teamEntity.get().members.add(userEntity.get());

            return teamRepository.save(teamEntity.get());
        }
        return null;
    }
}
