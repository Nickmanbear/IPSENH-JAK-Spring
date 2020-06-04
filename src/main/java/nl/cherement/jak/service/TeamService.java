package nl.cherement.jak.service;

import nl.cherement.jak.entity.TeamEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.TeamRepository;
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
    UserService userService;

    public TeamService(TeamRepository repository) {
        super(repository);
    }

    public List<TeamEntity> findByMember(String memberName) {
        UserEntity userEntity = userService.findByUsername(memberName);

        return teamRepository.findByMembers(userEntity);
    }

    public TeamEntity saveNew(String teamName, Principal principal) {
        UserEntity userEntity = userService.findByUsername(principal.getName());
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
        Optional<UserEntity> userEntity = userService.findById(memberId);
        if (teamEntity.isPresent() && userEntity.isPresent()) {
            teamEntity.get().members.add(userEntity.get());

            return teamRepository.save(teamEntity.get());
        }
        return null;
    }

    public TeamEntity deleteMember(Long teamId, Long memberId) {
        Optional<TeamEntity> optionalTeam = teamRepository.findById(teamId);
        Optional<UserEntity> optionalUser = userService.findById(memberId);
        if (optionalTeam.isPresent() && optionalUser.isPresent()) {
            optionalTeam.get().members.remove(optionalUser.get());

            return teamRepository.save(optionalTeam.get());
        }
        return null;
    }
}
