package com.example.mbti.controller;

import com.example.mbti.domain.Board;
import com.example.mbti.domain.Comment;
import com.example.mbti.repository.CommentRepository;
import com.example.mbti.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentRepository commentRepository;

    @GetMapping("/")
    public String showBoardList(Model model) {
        model.addAttribute("posts", boardService.getAllPosts());
        return "board/list";
    }
    @GetMapping("/board/write")
    public String showWriteForm() {
        return "board/write";
    }

    @PostMapping("/board/write")
    public String writeBoard(Board board) {
        boardService.savePost(board);
        return "redirect:/";
    }
    // 수정 폼으로 이동
    @GetMapping("/board/edit/{bno}")
    public String showEditForm(@PathVariable Long bno, Model model) {
        Board post = boardService.getPostById(bno);
        model.addAttribute("post", post);
        return "board/edit";
    }

    // 수정 처리
    @PostMapping("/board/update")
    public String updatePost(Board updatedPost) {
        boardService.updatePost(updatedPost);
        return "redirect:/board/detail/" + updatedPost.getBno();
    }

    // 삭제 처리
    @GetMapping("/board/delete/{bno}")
    public String deletePost(@PathVariable Long bno) {
        boardService.deletePost(bno);
        return "redirect:/";
    }

    @GetMapping("/board/detail/{bno}")
    public String showPostDetail(@PathVariable Long bno, Model model) {
        Board post = boardService.getPostAndIncreaseCount(bno);
        List<Comment> comments = commentRepository.findByBnoOrderByCreatedAtAsc(bno);

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "board/detail";
    }


}
