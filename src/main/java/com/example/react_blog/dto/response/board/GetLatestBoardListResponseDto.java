package com.example.react_blog.dto.response.board;

import com.example.react_blog.common.ResponseCode;
import com.example.react_blog.common.ResponseMessage;
import com.example.react_blog.dto.object.BoardListItem;
import com.example.react_blog.dto.response.ResponseDto;
import com.example.react_blog.entity.BoardListViewEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetLatestBoardListResponseDto extends ResponseDto {

    private List<BoardListItem> latestList;

    private GetLatestBoardListResponseDto(List<BoardListViewEntity> boardEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.latestList = BoardListItem.getList(boardEntities);
    }

    public static ResponseEntity<GetLatestBoardListResponseDto> success(List<BoardListViewEntity> boardEntities){
        GetLatestBoardListResponseDto response = new GetLatestBoardListResponseDto(boardEntities);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
