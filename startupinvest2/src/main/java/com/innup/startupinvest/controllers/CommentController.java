package com.innup.startupinvest.controllers;

import com.innup.startupinvest.models.ReplyComment;
import com.innup.startupinvest.models.StartUp;
import com.innup.startupinvest.models.StartUpComment;
import com.innup.startupinvest.models.User;
import com.innup.startupinvest.repositories.StartUpCommentRepository;
import com.innup.startupinvest.repositories.StartupRepositories;
import com.innup.startupinvest.services.CommentService;
import com.innup.startupinvest.services.StartUpService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final StartUpService startUpService;
    private final StartUpCommentRepository startUpCommentRepository;
    private final StartupRepositories startupRepositories;


    @PostMapping("/startup/{id}/createComment")
    public String createComment (StartUpComment startUpComment, @PathVariable Long id, Principal principal, @RequestParam(name = "comment-area") String comment) {
        commentService.createComment( startUpComment, id, principal, comment);
        return "redirect:/startup/" + id;
    }
    //прописать в start-up-info
    @PostMapping("/startup/deleteComment/{id}")
    public String deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        return "start-up-info";
    }
    @PostMapping("/startup/{startupId}/replyComment/{commentId}")
    public String replyComment(ReplyComment replyComment, @PathVariable(name = "startupId") Long startupId,@PathVariable(name="commentId") Long commentId, Principal principal, @RequestParam(name = "commentReply-area") String comment){
        commentService.replyComment(replyComment, commentId, principal, comment);
        return "redirect:/startup/" + startupId;
    }
    //прописать в start-up-info
    @PostMapping("/startup/deleteReplyComment/{id}")
    public String deleteReplyComment(@PathVariable Long id) {
        commentService.deleteReplyComment(id);
        return "start-up-info";
    }
}
