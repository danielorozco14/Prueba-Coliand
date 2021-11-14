package com.daniel.coliand.controllers.repos;

import com.daniel.coliand.models.repos.NewRepo;
import com.daniel.coliand.models.repos.Repo;
import com.daniel.coliand.models.users.User;
import com.daniel.coliand.services.repos.RepoService;
import com.daniel.coliand.services.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1")
public class RepoController {
    private final RepoService repoService;

    public RepoController( RepoService repoService) {
        this.repoService = repoService;
    }

    @GetMapping("/repositories")
    public List<Repo> getUserReposFromGithub(){
        return repoService.getUserRepos();
    }

    @PostMapping("/repositories")
    public Repo addNewRepoToGithub(@RequestBody NewRepo new_repo){
       return repoService.addNewRepo(new_repo.getName(), new_repo.isAutoInit(), new_repo.isPrivate(), new_repo.getGitignore_template());
    }

    @PatchMapping("/repos/{username}/{repoName}")
    public Repo patchExistingRepoInGitHub(@PathVariable String repoName,@PathVariable String username, @RequestBody NewRepo patchRepo){
        return repoService.patchRepo(repoName,username, patchRepo);
    }

}
