package com.example.mbti.controller;

import com.example.mbti.domain.Board;
import com.example.mbti.domain.Comment;
import com.example.mbti.repository.BoardRepository;
import com.example.mbti.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @PostMapping("/comment")
    public String writeComment(Comment comment) {
        commentRepository.save(comment);
        return "redirect:/board/detail/" + comment.getBno();
    }

}
