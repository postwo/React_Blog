package com.example.react_blog.service.implement;

import com.example.react_blog.dto.request.board.PostBoardRequestDto;
import com.example.react_blog.dto.request.board.PostCommentRequestDto;
import com.example.react_blog.dto.response.ResponseDto;
import com.example.react_blog.dto.response.board.*;
import com.example.react_blog.entity.BoardEntity;
import com.example.react_blog.entity.CommentEntity;
import com.example.react_blog.entity.FavoriteEntity;
import com.example.react_blog.entity.ImageEntity;
import com.example.react_blog.repository.*;
import com.example.react_blog.repository.resultSet.GetBoardResultSet;
import com.example.react_blog.repository.resultSet.GetFavoriteListResultSet;
import com.example.react_blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final FavoriteRepository favoriteRepository;
    private final CommentRepository commentRepository;

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {

        try {

            boolean exitedEmail = userRepository.existsByEmail(email);//존재하는지 유저 확인
            if (!exitedEmail) return PostBoardResponseDto.notExistUser();

            BoardEntity boardEntity = new BoardEntity(dto,email);
            boardRepository.save(boardEntity); // 게시물 등록

            int boardNumber= boardEntity.getBoardNumber();

            List<String> boardImageList = dto.getBoardImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            //imageentity 에있는 image 컬럼하고 이름 일치
            for (String image : boardImageList) {
                ImageEntity imageEntity = new ImageEntity(boardNumber, image);
                imageEntities.add(imageEntity);
            }

            // save를 해도 되기는 하는데 너무 한번에 데이터가 넘어가므로 saveAll을 사용
            imageRepository.saveAll(imageEntities);//게시글 이미지 저장

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostBoardResponseDto.success();

    }

    //게시글 불러오기
    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {
        GetBoardResultSet resultSet= null;
        List<ImageEntity> imageEntities = new ArrayList<>();

        try {
            resultSet = boardRepository.getBoard(boardNumber);
            if (resultSet == null) return GetBoardResponseDto.notExistBoard();

            imageEntities = imageRepository.findByBoardNumber(boardNumber);

            //뷰카운트 증가
            BoardEntity boardEntity = boardRepository.findByboardNumber(boardNumber);
            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetBoardResponseDto.success(resultSet,imageEntities);
    }

    //좋아요
    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email) {

        try {
            boolean exitedUser = userRepository.existsByEmail(email);//존재하는지 유저 확인
            if (!exitedUser) return PutFavoriteResponseDto.notExistUser();

            BoardEntity boardEntity = boardRepository.findByboardNumber(boardNumber);
            if (boardEntity == null) return PutFavoriteResponseDto.notExistBoard();

            //좋아요 증가,감소
            FavoriteEntity favoriteEntity = favoriteRepository.findByBoardNumberAndUserEmail(boardNumber,email);
            if (favoriteEntity == null) {
                favoriteEntity = new FavoriteEntity(email,boardNumber);
                favoriteRepository.save(favoriteEntity);
                boardEntity.increaseFavoriteCount();
            }
            else {
                favoriteRepository.delete(favoriteEntity);
                boardEntity.decreaseFavoriteCount();
            }

            boardRepository.save(boardEntity);

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PutFavoriteResponseDto.success();
    }

    // 좋아요 게시물 가져오기
    @Override
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber) {

        List<GetFavoriteListResultSet> resultSets = new ArrayList<>();

        try {
            boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);//존재하는지 게시물 확인
            if (!existedBoard) return GetFavoriteListResponseDto.notExistBoard();

            resultSets = favoriteRepository.getFavoriteList(boardNumber);

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetFavoriteListResponseDto.success(resultSets);
    }

    //댓글 작성
    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber ,String email) {
        try{

            BoardEntity boardEntity = boardRepository.findByboardNumber(boardNumber);//존재하는지 게시물 확인
            if (boardEntity == null) return PostCommentResponseDto.notExistBoard();

            boolean exitedUser = userRepository.existsByEmail(email);//존재하는지 유저 확인
            if (!exitedUser) return PostCommentResponseDto.notExistUser();

            CommentEntity commentEntity = new CommentEntity(dto,boardNumber,email);
            commentRepository.save(commentEntity);

            boardEntity.increaseCommentCount();
            boardRepository.save(boardEntity);

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostCommentResponseDto.success();
    }
}
