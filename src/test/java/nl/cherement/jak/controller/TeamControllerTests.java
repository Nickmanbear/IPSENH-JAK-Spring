package nl.cherement.jak.controller;

import nl.cherement.jak.entity.TeamEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class TeamControllerTests {

    private final TeamDTO teamDTO = new TeamDTO();
    private final TeamEntity team = new TeamEntity();
    private final TeamEntity team2 = new TeamEntity();
    private List<TeamEntity> teams;

    private final UserEntity user = new UserEntity();
    private final UserEntity user2 = new UserEntity();
    private List<UserEntity> users;

    @Autowired
    private TeamController controller;

    @MockBean
    private TeamService service;

    @MockBean
    private Authentication authentication;

    @BeforeEach
    public void initialize() {
        teamDTO.id = 1;

        user.id = 1;
        user.username = "user";

        user2.id = 2;
        user2.username = "user2";

        users = new ArrayList<UserEntity>();
        users.add(user);
        users.add(user2);

        team.id = 1;
        team.name = "team1";
        team.leader = user;
        team.members = users;

        team2.id = 2;
        team2.name = "team2";
        team2.leader = user2;
        team2.members = users;

        teams = new ArrayList<TeamEntity>();
        teams.add(team);
        teams.add(team2);

        doReturn(teams).when(service).findByMember(any());
        doReturn(Optional.of(team)).when(service).findById(any(Authentication.class), any());
        doReturn(team).when(service).save(any(Authentication.class), any());
        doNothing().when(service).delete(any(Authentication.class), any());
        doReturn(team).when(service).addMember(any(Authentication.class), any(), any());
        doReturn(team).when(service).deleteMember(any(Authentication.class), any(), any());
    }

    @Test
    void DTO() {
        teamDTO.id = 1;
        teamDTO.name = "team1";
        teamDTO.leader = user;
        teamDTO.members = users;

        assertEquals("TeamEntity [id=" + teamDTO.id + ", name=" + teamDTO.name +
                ", leader=" + teamDTO.leader + ", members=" + teamDTO.members +
                "]", teamDTO.toEntity().toString());
    }

    @Test
    void findAll() {
        assertSame(teams, controller.findAll(authentication));
    }

    @Test
    void findById() {
        assertSame(team, controller.findById(authentication, 1L).get());
    }

    @Test
    void save() {
        assertSame(team, controller.save(authentication, teamDTO));
    }

    @Test
    void delete() {
        assertSame(HttpStatus.OK, controller.delete(authentication, team));
    }

    @Test
    void addMember() {
        assertSame(team, controller.addMember(authentication, 1L, 1L));
    }

    @Test
    void deleteMember() {
        assertSame(team, controller.deleteMember(authentication, 1L, 1L));
    }
}
