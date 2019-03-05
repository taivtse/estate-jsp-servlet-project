package com.laptrinhjavaweb.constant;

import java.io.File;

public class SystemConstant {
    public static final String BASE_UPLOAD_PATH = "/Users/vothanhtai/Documents/WorkSpace/Java/LRHTeam/estate-project/resources".replace("/", File.separator);
    public static final String BUILDING_UPLOAD_PATH = "building";

    public static final String REDIRECT_URL = "redirect:/";
    public static final String COMMAND = "command";
    public static final String SORT_ASC = "asc";
    public static final String SORT_DESC = "desc";
    public static final String MESSAGE_RESPONSE = "messageResponse";
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String PNOTIFY = "pNotify";
    public static final Integer PASSWORD_GENERATE_LENGTH = 10;

    public static final String SESSION_USER = "session_user";
    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_USER = "USER";
}
