package kr.com.inspect.controller;

import kr.com.inspect.dto.ResponseData;
import kr.com.inspect.rule.RunSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * SQL 실행 관련 Controller
 * @author Wooyoung
 * @version 1.0
 */
@Controller
public class SQLController {
    /**
     * SQL 실행 객체를 필드로 선언
     */
    @Autowired
    private RunSQL runSQL;

    /**
     * SQL 실행 페이지로 이동
     * @return SQL 실행 페이지
     */
    @GetMapping("/runSQLPage")
    public String sqlExecutePage(){
        return "postgreSQL/runSQL";
    }

    /**
     * query를 받아서 SQL을 실행
     * @param response
     * @throws Exception 예외처리
     */
    @ResponseBody
    @RequestMapping(value = "/runSQL", method = RequestMethod.POST)
    public void runSQL(HttpServletResponse response, @RequestParam("query") String query) throws Exception {
        System.out.println("runSQL PostMapping");
        ResponseData responseData = new ResponseData(); //ajax 응답 객체

        // 앞뒤 공백 제거, 소문자 전환
        System.out.println("controller query : " + query);
        responseData = runSQL.run(responseData, query.toLowerCase().trim());
        responseData.responseJSON(response, responseData);
    }
}