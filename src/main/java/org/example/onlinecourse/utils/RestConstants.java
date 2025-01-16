package org.example.onlinecourse.utils;


import org.example.onlinecourse.controller.interfaces.AuthController;

public interface RestConstants<C> {
    String BASE_PATH = "/api";
    String BASE_PATH_V1 = BASE_PATH + "/v1";
    String[] OPEN_PAGES = {
            RestConstants.BASE_PATH + "/swagger-ui/**",
            RestConstants.BASE_PATH + "/swagger-ui.html",
            AuthController.BASE_PATH,
            BASE_PATH_V1 + "/region/**"

    };


    String AUTHENTICATION_HEADER = "Authorization";

    String TOKEN_TYPE = "Bearer ";

    String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";


    String NUMBER_GREATER = "NUMBER_GREATER";
    String ONE_OF_LIST = "ONE_OF_LIST";
    String USER_ENTERED = "USER_ENTERED";
    String DEFAULT_PAGE = "0";
    String DEFAULT_PAGE_SIZE_FOR_SEARCH = "5";
    String DEFAULT_PAGE_SIZE = "10";
    String APPLICATION_NAME = "DIABLO AIRWAYS";
    String WHERE_CLAUSE = "deleted=false";
    String SQL_DELETED = "";
    String BEARER_TOKEN = "Bearer ";
    String UZBEK_PHONE_NUMBER_REGEX = "\\+998(99|33|90|94|88|70)\\d{7}";
}

