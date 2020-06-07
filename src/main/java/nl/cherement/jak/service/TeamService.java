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
            teamEntity.members = new ArrayList<>();
            teamEntity.members.add(userEntity);
            teamEntity.leader = userEntity;
        }

        return teamRepository.save(teamEntity);
    }

    public TeamEntity addMember(Authentication authentication, TeamEntity teamEntity, UserEntity userEntity) {
        teamEntity.members.add(userEntity);

        return teamRepository.save(teamEntity);
    }

    public TeamEntity deleteMember(Authentication authentication, TeamEntity teamEntity, UserEntity userEntity) {
            teamEntity.members.remove(userEntity);

            return teamRepository.save(teamEntity);
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
