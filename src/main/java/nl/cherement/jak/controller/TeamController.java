package nl.cherement.jak.controller;

import nl.cherement.jak.entity.TeamEntity;
import nl.cherement.jak.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
    public List<TeamEntity> findAll(Authentication authentication) {
        return service.findByMember(authentication.getName());
    }

    @GetMapping("/{id}")
    public Optional<TeamEntity> findById(Authentication authentication, @PathVariable("id") Long id) {
        return service.findById(authentication, id);
    }

    @PostMapping
    public TeamEntity save(Authentication authentication, @RequestBody TeamDTO teamDTO) {
        return service.save(authentication, teamDTO.toEntity());
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(Authentication authentication, @PathVariable("id") Long id) {
        service.deleteById(authentication, id);

        return HttpStatus.OK;
    }

    @PostMapping("/new/{teamName}")
    public TeamEntity saveNew(@PathVariable("teamName") String teamName, Principal principal) {
        return service.saveNew(teamName, principal);
    }

    @PostMapping("/member/{teamId}/{userId}")
    public TeamEntity addMember(@PathVariable("teamId") Long teamId, @PathVariable("userId") Long userId) {
        return service.addMember(teamId, userId);
    }

    @DeleteMapping("/member/{teamId}/{userId}")
    public TeamEntity deleteMember(@PathVariable("teamId") Long teamId, @PathVariable("userId") Long userId) {
        return service.deleteMember(teamId, userId);
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
