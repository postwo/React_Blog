package com.example.react_blog.common;

public interface ResponseCode {

    //public static final 인터페이스를 사용하면 이걸 안붙여도 알아서 붙는다
    //public static final String Success = "su";

    //HTTP STATUS 200
    String SUCCESS ="SU";

    //HTTP STATUS 400
    String VALIDATION_FAIL = "VF";
    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_NICKNAME = "DN";
    String DUPLICATE_TEL_NUMBER= "DT";
    String NOT_EXISTED_USER = "NU";
    String NOT_EXISTED_BOARD = "NB";


    //HTTP STATUS 401
    String SIGN_IN_FAIL = "SF";
    String AUTHORIZATION_FAIL = "AF";


    //HTTP STATUS 403
    String NO_PERMISSION="NP";


    //HTTP STATUS 500
    String DATABASE_ERROR = "DBE";

}
