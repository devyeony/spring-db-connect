package kr.com.inspect.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.com.inspect.dto.CustomLibrary;
import kr.com.inspect.dto.ResponseData;
import kr.com.inspect.dto.Rule;
import kr.com.inspect.dto.RuleLog;

/**
 * 전사규칙에 관한 Service 인터페이스
 * @author Yeonhee Kim
 * @version 1.0
 */
public interface RuleService {
	
	/**
	 * 전사규칙 대분류, 중분류, 소분류 카테고리 리스트를 반환함
	 * @param top_level_id 전사규칙 대분류 아이디
	 * @param middle_level_id 전사규칙 중분류 아이디
	 * @return 전사규칙 대분류, 중분류, 소분류 카테고리 리스트
	 */
	public List<Rule> getRuleCategory(String top_level_id, 
											String middle_level_id);
	
	/**
	 * 해당되는 전사규칙 리스트를 가지고 옴
	 * @param top_level_id 전사규칙 대분류 아이디
	 * @param middle_level_id 전사규칙 중분류 아이디
	 * @param bottom_level_id 전사규칙 소분류 아이디
	 * @return 해당되는 전사규칙 리스트
	 */
	public List<Rule> getRuleList(String top_level_id, 
									String middle_level_id, 
									String bottom_level_id);
	
	/**
	 * 선택된 카테고리에 해당되는 전사규칙 리스트를 조인해서 가져옴
	 * @param rule_type 룰 타입(전체/SQL/메서드)
	 * @param top_level_id 전사규칙 대분류 아이디
	 * @param middle_level_id 전사규칙 중분류 아이디
	 * @param bottom_level_id 전사규칙 소분류 아이디
	 * @param function_name 페이지의 번호를 클릭했을 때 호출되는 자바스크립트 함수명 또는 게시글 조회를 요청하는 함수명을 저장할 변수
	 * @param current_page_no 현재 화면에 출력되고 있는 페이지 번호 또는 페이지의 번호를 클릭했을 때에 번호를 저장할 변수
	 * @param count_per_page 한 화면에 출력되는 페이지의 수를 저장할 변수
	 * @param count_per_list 한 화면에 출력되는 게시글의 수를 저장할 변수
	 * @param search_word 검색어
	 * @return 선택된 카테고리에 해당되는 전사규칙 리스트
	 */
	public ResponseData getRuleListByPaging(String rule_type,
												String top_level_id, 
												String middle_level_id,
												String bottom_level_id,
												String function_name, 
												int current_page_no,
												int count_per_page,
												int count_per_list,
												String search_word);
	
	/**
	 * 룰 로그 테이블을 페이징 처리하여 가져옴
	 * @param data RuleLog 테이블의 외래키인 using_log_no
	 * @param function_name 페이지의 번호를 클릭했을 때 호출되는 자바스크립트 함수명 또는 게시글 조회를 요청하는 함수명을 저장할 변수
	 * @param current_page_no 현재 화면에 출력되고 있는 페이지 번호 또는 페이지의 번호를 클릭했을 때에 번호를 저장할 변수
	 * @param count_per_page 한 화면에 출력되는 페이지의 수를 저장할 변수
	 * @param count_per_list 한 화면에 출력되는 게시글의 수를 저장할 변수
	 * @param search_word 검색어
	 * @return 룰 로그 테이블
	 */
	public ResponseData getRuleLog(int using_log_no,
									String function_name, 
									int current_page_no,
									int count_per_page,
									int count_per_list,
									String search_word);
	
	/**
	 * 사용 기록 번호로 디테일한 룰 로그 목록을 가져옴
	 * @param using_log_no 사용 기록 번호
	 * @return 디테일한 룰 로그 목록
	 */
	public List<RuleLog> getAllRuleLogDetailByUsingLogNo(int using_log_no);
	
	/**
	 * 대분류/중분류/소분류를 DB에 등록함
	 * @param level 해당되는 분류(대분류/중분류/소분류)
	 * @param rule DB 등록을 위한 Rule 객체
	 * @return DB에 등록한 row의 수
	 */
	public int registerRule(String level, Rule rule);
	
	/**
	 * 대분류/중분류/소분류 아이디로 해당되는 항목을 삭제함
	 * @param level 해당되는 분류(대분류/중분류/소분류)
	 * @param id 대분류/중분류/소분류 아이디
	 * @return DB에서 삭제한 row의 수
	 */
	public int deleteRule(String level, Rule rule);
	
	public Rule getRuleBottomLevel(int bottom_level_id);
	
	/**
	 * 사용자가 입력한 Rule 코드를 DB에 업데이트함
	 * @param rule 코드 업데이트를 위한 Rule 객체
	 * @return 컴파일 성공 여부, DB 업데이트 여부, 실행 결과값 또는 예외 메세지
	 * @throws Exception 예외
	 */
	public Map<String, Object> updateRuleContents(Rule rule) throws Exception;
	
	/**
	 * Rule 클래스 파일을 실행시킴
	 * @param list Rule 목록
	 * @throws Exception 예외
	 */
	public void runRuleCompiler(List<Rule> list) throws Exception;
	
	/**
	 * 클래스 아이디로 클래스 정보, 필드 정보, 생성자 정보, 메소드 정보를 가져옴
	 * @param class_id DB 상의 클래스 아이디
	 * @return 클래스에 관한 전반적인 정보를 담은 Map
	 */
	public Map<String, Object> getApiDesc(int class_id);
	
	/**
	 * 사용자가 import하고자 하는 커스텀 라이브러리 파일을 업로드함 
	 * @param customFile 사용자가 업로드한 커스텀 라이브러리 파일
	 * @param class_package 클래스 파일일 경우 패키지명
	 * @throws Exception 예외
	 */
	public void uploadCustomLibrary(List<MultipartFile> customFile, String class_package) throws Exception;
	
	/**
	 * 사용자가 import하고자 하는 커스텀 라이브러리를 등록함
	 * @param customLibrary 등록하고자 하는 커스텀 라이브러리 객체
	 * @return RuleLog에 등록될 메세지
	 */
	public String registerCustomLibrary(CustomLibrary customLibrary);
	
	/**
	 * 사용자 아이디로 사용자가 추가한 커스텀 라이브러리 목록을 가져옴
	 * @param creator 사용자 아이디
	 * @return 사용자가 추가한 커스텀 라이브러리 목록
	 */
	public List<CustomLibrary> getAllCustomLibraryByCreator(String creator);
	
	/**
	 * 해당되는 커스텀 라이브러리 파일을 삭제하고 DB에서도 삭제함
	 * @param customLibrary 삭제할 CustomLibrary 객체
	 * @return DB에서 삭제된 row의 수
	 */
	public int deleteCustomLibrary(CustomLibrary customLibrary);
}
