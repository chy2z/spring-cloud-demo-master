package cn.demo.service.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractController {


    @ExceptionHandler({Exception.class})
    public Map<String, String> exceptionHandler(Exception e, WebRequest req) {
        System.err.println("=================");
        System.err.println(req.toString());
        System.err.println(req.getRemoteUser());
        System.err.println(req.getContextPath());
        System.err.println("=============");
        Map<String, String> map = new HashMap<>();
        map.put("code", "0");
        map.put("msg", e.getMessage());
        return map;
    }
}
