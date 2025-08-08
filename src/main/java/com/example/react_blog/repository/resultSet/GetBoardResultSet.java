package com.example.react_blog.repository.resultSet;


//이걸 만든이유는 반환 받는 타입인 BoardEntity에 writerProfileImage,writerNickname등이 없어서 여기서 만들어서 처리
public interface GetBoardResultSet {

    Integer getBoardNumber();
    String getTitle();
    String getContent();
    String getWriterDatetime();
    String getWriterEmail();
    String getWriterNickname();
    String getWriterProfileImage();
}
