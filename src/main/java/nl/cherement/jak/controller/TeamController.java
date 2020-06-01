package nl.cherement.jak.controller;

import nl.cherement.jak.entity.TeamEntity;
import nl.cherement.jak.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService service;

    @GetMapping
    public List<TeamEntity> findAll(Principal principal) {
        return service.findByMember(principal.getName());
    }

    @GetMapping("/{id}")
    public Optional<TeamEntity> findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public TeamEntity save(@RequestBody TeamDTO teamDTO) {
        return service.save(teamDTO.toEntity());
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);

        return HttpStatus.OK;
    }

    @PostMapping("/member/{teamId}/{userId}")
    public TeamEntity addUser(@PathVariable("teamId") Long teamId, @PathVariable("userId") Long userId) {
        return service.addMember(teamId, userId);
    }
}

class TeamDTO extends TeamEntity {

    TeamEntity toEntity() {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.id = id;
        teamEntity.name = name;
        teamEntity.leader = leader;
        teamEntity.members = members;

        return teamEntity;
    }
}
