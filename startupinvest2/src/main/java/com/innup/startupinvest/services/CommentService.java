package com.innup.startupinvest.services;

import com.innup.startupinvest.models.ReplyComment;
import com.innup.startupinvest.models.StartUpComment;
import com.innup.startupinvest.repositories.ReplyCommentRepository;
import com.innup.startupinvest.repositories.StartUpCommentRepository;
import com.innup.startupinvest.repositories.StartupRepositories;
import com.innup.startupinvest.repositories.UserRepositories;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.security.Principal;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final StartUpCommentRepository startUpCommentRepository;
    private final UserRepositories userRepositories;
    private final StartupRepositories startupRepositories;
    private final ReplyCommentRepository replyCommentRepository;
    public void createComment(StartUpComment startUpComment, Long id, Principal principal, String comment) {
        startUpComment.setUser(userRepositories.findByEmail(principal.getName()));
        startUpComment.setComment(comment);
        startUpComment.setStartUp(startupRepositories.findById(id).orElse(null));
        startUpCommentRepository.save(startUpComment);
    }
    public void deleteComment(Long id){
        startUpCommentRepository.deleteById(id);
    }
    public void replyComment(ReplyComment replyComment, Long id, Principal principal, String comment){
        replyComment.setComment(comment);
        replyComment.setUser(userRepositories.findByEmail(principal.getName()));
        replyComment.setStartUpComment(startUpCommentRepository.findById(id).orElse(null));
        replyCommentRepository.save(replyComment);
    }
    public void deleteReplyComment(Long id){
        replyCommentRepository.deleteById(id);
    }
}
