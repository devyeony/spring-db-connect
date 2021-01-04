package kr.com.inspect.rule;

import kr.com.inspect.dto.ResponseData;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * SQL 실행
 * @author Wooyoung
 * @version 1.0
 */
@Component
public class RunSQL {
    /**
     * query를 받아 실행하는 메서드
     * @param query 실행할 쿼리문
     */
    public ResponseData run(ResponseData responseData, String query) {
        System.out.println("RunSQL run query: " + query);
        if(query.length() < 6){
            System.out.println(query.length() < 6);
            responseData.setItem("잘못된 쿼리입니다. 다시 입력해주세요.");
            return responseData;
        }
        String type = query.substring(0, 6);
        List<Object> list = new ArrayList<>();
        List<List<Object>> listList = new ArrayList<>();

        Data data = new Data();
        Connection con = null;
        ResultSet resultSet = null;
        int result, columnCount;
        PreparedStatement preparedStatement = null;
        try{
            con = data.getConnect();
            Statement statement = con.createStatement();

            switch (type){
                case "insert" :
                    result = statement.executeUpdate(query);
                    responseData.setCode("insert");
                    responseData.setItem(result + "개의 데이터가 삽입되었습니다.");
                    break;
                case "update" :
                    result = statement.executeUpdate(query);
                    responseData.setCode("update");
                    responseData.setItem(result + "개의 데이터가 업데이트되었습니다.");
                    break;
                case "select" :
                    resultSet = statement.executeQuery(query);
                    responseData.setCode("select");
                    columnCount = resultSet.getMetaData().getColumnCount();
                    for(int i=1; i<columnCount+1; i++){
                        list.add(resultSet.getMetaData().getColumnName(i));
                    }
                    listList.add(list);

                    while (resultSet.next()){
                        list = new ArrayList<>();
                        for(int i=1; i<columnCount+1; i++){
                            list.add(resultSet.getString(i));
                        }
                        listList.add(list);
                    }
                    responseData.setItem(listList);
                    break;
                case "delete" :
                    System.out.println("RunSQL - case delete");
                    result = statement.executeUpdate(query);
                    responseData.setCode("delete");
                    responseData.setItem(result + "개의 데이터가 삭제되었습니다.");
                    break;
                default:
                    responseData.setCode("error");
                    responseData.setItem("잘못된 쿼리입니다. 다시 입력해주세요.");
            }
            data.closeAll(resultSet, preparedStatement, con);
        }
        catch (Exception e){
            System.out.println("RunSQL - Exception");
            responseData.setCode("error");
            responseData.setItem("잘못된 쿼리입니다. 다시 입력해주세요.");
            return responseData;
        }
        return responseData;
    }
}