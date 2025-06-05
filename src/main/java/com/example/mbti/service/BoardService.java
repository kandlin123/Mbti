package com.example.mbti.service;

import com.example.mbti.domain.Board;
import com.example.mbti.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getAllPosts() {
        return boardRepository.findAll();
    }

    public void savePost(Board board) {
        board.setReadCount(0); // ✅ readCount로 수정
        boardRepository.save(board);
    }

    public Board getPostAndIncreaseCount(Long bno) {
        Board post = boardRepository.findById(bno)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다: " + bno));
        post.setReadCount(post.getReadCount() + 1); // ✅ readCount로 수정
        return boardRepository.save(post);
    }

    public Board getPostById(Long bno) {
        return boardRepository.findById(bno)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다: " + bno));
    }

    public void updatePost(Board updatedPost) {
        Board post = getPostById(updatedPost.getBno());
        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());
        boardRepository.save(post);
    }

    public void deletePost(Long bno) {
        boardRepository.deleteById(bno);
    }
}
