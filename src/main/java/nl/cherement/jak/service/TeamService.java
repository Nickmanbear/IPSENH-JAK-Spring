package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.TeamEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (teamEntity.id == 0L) {
            UserEntity userEntity = userService.findByUsername(authentication.getName());
            teamEntity.members = new ArrayList<>();
            teamEntity.members.add(userEntity);
            teamEntity.leader = userEntity;
        }

        return teamRepository.save(teamEntity);
    }

    public TeamEntity addMember(Authentication authentication, TeamEntity teamEntity, UserEntity userEntity) {
        if (!hasAccess(authentication, teamEntity)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "You do not have access to the object with id " + teamEntity.id);
        }
        teamEntity.members.add(userEntity);

        for (BoardEntity boardEntity : boardService.findByTeam(authentication, teamEntity.id)) {
            boardEntity.users.remove(userEntity);
            boardService.save(authentication, boardEntity);
        }

        return teamRepository.save(teamEntity);
    }

    public TeamEntity deleteMember(Authentication authentication, TeamEntity teamEntity, UserEntity userEntity) {
        if (!hasAccess(authentication, teamEntity)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "You do not have access to the object with id " + teamEntity.id);
        }
        teamEntity.members.remove(userEntity);

            return teamRepository.save(teamEntity);
    }

    @Override
    public void delete (Authentication authentication, TeamEntity team) {
        List<BoardEntity> boardEntities = boardService.findByTeam(authentication, team.id);
        for (BoardEntity board:boardEntities) {
            boardService.deleteTeam(board);
        }

        teamRepository.delete(team);
    }

    @Override
    boolean hasAccess(Authentication authentication, TeamEntity entity) {
        boolean userAccess = false;
        if (entity.leader.username.equals(authentication.getName())) {
            userAccess = true;
        }
        return userAccess;
    }
}
