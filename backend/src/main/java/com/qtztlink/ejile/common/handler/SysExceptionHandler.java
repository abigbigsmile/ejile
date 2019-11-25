package com.qtztlink.ejile.common.handler;

import com.qtztlink.ejile.common.exception.UnauthorizedException;

import java.util.HashMap;
import java.util.Map;

//@ControllerAdvice
public class SysExceptionHandler {
//    @ExceptionHandler(UnauthorizedException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUserNotExistsException(UnauthorizedException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        return map;
    }
}
