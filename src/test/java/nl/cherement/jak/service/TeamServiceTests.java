package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.TeamEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TeamServiceTests {
    private final TeamEntity team = new TeamEntity();
    private final TeamEntity team2 = new TeamEntity();
    private List<TeamEntity> teams;

    private final UserEntity user = new UserEntity();
    private final UserEntity user2 = new UserEntity();
    private List<UserEntity> users;
    private List<UserEntity> users2;

    private final BoardEntity board = new BoardEntity();
    private final BoardEntity board2 = new BoardEntity();
    private List<BoardEntity> boards;

    @Autowired
    private TeamService teamService;

    @MockBean
    private TeamRepository repository;

    @MockBean
    private UserService userService;

    @MockBean
    private BoardService boardService;

    @MockBean
    private Authentication authentication;

    @BeforeEach
    public void initialize() {
        user.id = 1;
        user.username = "user";

        user2.id = 2;
        user2.username = "user2";

        users = new ArrayList<UserEntity>();
        users.add(user);
        users.add(user2);

        users2 = new ArrayList<UserEntity>();
        users2.add(user);

        team.id = 1;
        team.name = "team1";
        team.leader = user;
        team.members = users;

        team2.id = 2;
        team2.name = "team2";
        team2.leader = user2;
        team2.members = users2;

        teams = new ArrayList<TeamEntity>();
        teams.add(team);
        teams.add(team2);

        board.id = 1;
        board.name = "board";
        board.users = users;
        board.team = team;

        board2.id = 2;
        board2.name = "board2";
        board2.users = users;

        boards = new ArrayList<BoardEntity>();
        boards.add(board);
        boards.add(board2);

        doReturn(user).when(userService).findByUsername(any());
        doReturn(teams).when(repository).findByMembers(any());
        doReturn(team).when(repository).save(any());
        doReturn(Optional.of(team)).when(repository).findById(any());
        doReturn(Optional.of(user2)).when(userService).findById(any(Authentication.class), any());
        doReturn(boards).when(boardService).findByTeam(any(Authentication.class), any());
        doReturn(board).when(boardService).deleteTeam(any());
        doNothing().when(repository).deleteById(any());
    }

    @Test
    void findByMember() {
        assertSame(teams, teamService.findByMember("me"));
    }

    @Test
    void save() {
        team.id = 0;
        assertSame(team, teamService.save(authentication, team));
    }

    @Test
    void addMember() {
        assertSame(team, teamService.addMember(team, user));
    }

    @Test
    void deleteMember() {
        assertSame(team, teamService.deleteMember(team, user));
    }

    @Test
    void delete() {
        teamService.delete(authentication, team);

        verify(repository, times(1)).delete(any());
    }
}
