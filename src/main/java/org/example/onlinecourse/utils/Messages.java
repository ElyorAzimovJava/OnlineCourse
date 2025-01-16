package org.example.onlinecourse.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;

public interface Messages {
   String SUCCESS_SAVE_MESSAGE = "SUCCESSFULLY ADDED";
   String VERIFICATION_SUBJECT = "YOUR VERIFICATION PASSWORD";
   String NOT_FOUND = "%s NOT FOUND BY %s";
   String ANOTHER_DEVICE ="ANOTHER DEVICE";
   String BLOCKER_USER = "YOUR ACCOUNT IS BLOCKED";
   String NOT_VERIFIED = "YOUR ACCOUNT IS NOT VERIFIED";
   String INCORRECT_PASSWORD_OR_EMAIL = "INCORRECT PASSWORD OR EMAIL";
   String INCORRECT_VERIFICATION_CODE = "INCORRECT VERIFICATION CODE";
   String REFRESH_TOKEN_EXPIRED = "REFRESH TOKEN IS EXPIRED";
   String UNIVERSAL_ALREADY_EXISTS = " %s ALREADY EXISTS BY %s" ;
   String IMAGE_PATH = Paths.get("").toAbsolutePath() + "/src/main/resources/static/img";
   String VIDEO_PATH = Paths.get("").toAbsolutePath() + "/src/main/resources/static/video";
   String BASE_URL = "http://localhost:9090";
   String IMG_URL = "/img/";
   String VIDEO_URL = "/src/main/resources/video/";
}
