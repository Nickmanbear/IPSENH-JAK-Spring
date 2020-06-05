package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.TeamEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService extends AbstractService<TeamEntity> {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserService userService;

    @Autowired
    BoardService boardService;

    public TeamService(TeamRepository repository) {
        super(repository);
    }

    public List<TeamEntity> findByMember(String memberName) {
        UserEntity userEntity = userService.findByUsername(memberName);

        return teamRepository.findByMembers(userEntity);
    }

    @Override
    public TeamEntity save(Authentication authentication, TeamEntity teamEntity) {
        if (teamEntity.id == 0) {
            UserEntity userEntity = userService.findByUsername(authentication.getName());
            TeamEntity temporaryTeamEntity = new TeamEntity();
            temporaryTeamEntity.members = new ArrayList<>();
            temporaryTeamEntity.id = 0;
            temporaryTeamEntity.name = teamEntity.name;
            temporaryTeamEntity.leader = userEntity;
            temporaryTeamEntity.members.add(userEntity);
            teamEntity = temporaryTeamEntity;
        }

        return teamRepository.save(teamEntity);
    }

    public TeamEntity addMember(Authentication authentication, Long teamId, Long memberId) {
        Optional<TeamEntity> teamEntity = teamRepository.findById(teamId);
        Optional<UserEntity> userEntity = userService.findById(authentication, memberId);
        if (teamEntity.isPresent() && userEntity.isPresent()) {
            teamEntity.get().members.add(userEntity.get());

            return teamRepository.save(teamEntity.get());
        }
        return null;
    }

    public TeamEntity deleteMember(Authentication authentication, Long teamId, Long memberId) {
        Optional<TeamEntity> optionalTeam = teamRepository.findById(teamId);
        Optional<UserEntity> optionalUser = userService.findById(authentication, memberId);
        if (optionalTeam.isPresent() && optionalUser.isPresent()) {
            optionalTeam.get().members.remove(optionalUser.get());

            return teamRepository.save(optionalTeam.get());
        }
        return null;
    }

    @Override
    public void delete (Authentication authentication, TeamEntity team) {
        List<BoardEntity> boardEntities = boardService.findByTeam(authentication, team.id);
        for (BoardEntity board:boardEntities) {
            boardService.deleteTeam(board.id);
        }

        teamRepository.delete(team);
    }

    @Override
    boolean hasAccess(Authentication authentication, TeamEntity entity) {
        return true;
    }
}
