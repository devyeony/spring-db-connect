package kr.com.inspect.service;

import java.util.List;

import kr.com.inspect.dto.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * PostgreSQL Service Interface
 * @author Yeonhee Kim
 * @author Wooyoung Lee
 * @version 1.0
 *
 */

public interface PostgreService {
	
	/**
	 * metadata 아이디로 JSON 파일을 생성하여 다운로드하거나 메일을 전송함
	 * @param response 응답 객체
	 * @param type 요청의 종류(다운로드/메일)
	 * @param email 이메일 주소
	 * @param metadata_id metadata id
	 * @param jsonOutputPath JSON 파일을 생성할 경로
	 */
	public void makeMetadataJSON(HttpServletResponse response, 
			String type, String email, int metadata_id, String jsonOutputPath);
	
	/**
	 * Metadata 테이블을 모두 가지고 옴
	 * @return Metadata 테이블 리스트
	 */
	public List<Metadata> getMetadata();
	
	/**
	 * id로 해당되는 Metadata 테이블을 가져옴
	 * @param id Metadata 테이블의 id 값
	 * @return id 값에 해당하는 metadata 테이블들의 값을 리턴
	 */
	public Metadata getMetadataById(Integer id);
	
	/**
	 * metadataId로 해당되는 Utterance 테이블을 가져옴
	 * @param metadataId Utterance 테이블의 metadataId 값
	 * @return metadataId 값에 해당하는 Utterance 테이블들의 값을 리스트에 담아 리턴
	 */
	public List<Utterance> getUtteranceUsingMetadataId(Integer metadataId);

	/**
	 * utterance id 로 해당되는 utterance를 가져옴
	 * @param id utterance에 해당하는 utterance id 값
	 * @return utterance 리턴
	 */
	public Utterance getUtteranceUsingId(String id);

	/**
	 * JSON 파일들을 업로드해서 PostgreSQL에 넣음
	 * @param path 파일 디렉토리
	 * @param jsonFile json 파일
	 */
	public void insertJSONUpload(String path, List<MultipartFile> jsonFile) ;

	/**
	 * 서버 디렉토리 안의 json 파일을 PostgreSQL에 넣음
	 * @param path 파일 디렉토리
	 * @return DB의 데이터 여부를 확인하고 값을 리턴함
	 */
	public String insertJSONDir(String path) ;

	/**
	 * xlsx 파일들을 업로드해서 PostgreSQL에 넣음
	 * @param path 파일 디렉토리
	 * @param xlsxFile 엑셀 파일
	 * @return DB의 데이터 여부를 확인하고 값을 리턴함
	 */
	public boolean insertXlsxUpload(String path, List<MultipartFile> xlsxFile);

	/**
	 * Metadata 테이블과 Program 테이블을 조인해서 전체 테이블을 가져옴
	 * @param data 데이터 타입 유형(전체/강의/회의/고객응대/상담)
	 * @return Metadata 테이블과 Program 테이블을 조인한 전체 테이블
	 */
	public List<Metadata> getMetadataAndProgram(String data);
	
	/**
	 * Metadata 테이블과 Program 테이블을 조인해서 페이징 처리하여 가져옴
	 * @param data 데이터 타입 유형(전체/강의/회의/고객응대/상담)
	 * @param function_name 페이지의 번호를 클릭했을 때 호출되는 자바스크립트 함수명 또는 게시글 조회를 요청하는 함수명을 저장할 변수
	 * @param current_page_no 현재 화면에 출력되고 있는 페이지 번호 또는 페이지의 번호를 클릭했을 때에 번호를 저장할 변수
	 * @param count_per_page 한 화면에 출력되는 페이지의 수를 저장할 변수
	 * @param count_per_list 한 화면에 출력되는 게시글의 수를 저장할 변수
	 * @param search_word 검색어
	 * @return Metadata 테이블과 Program 테이블을 조인하여 페이징 처리한 테이블
	 */
	public ResponseData getMetadataAndProgram(String data, 
											String function_name, 
											int current_page_no,
											int count_per_page,
											int count_per_list,
											String search_word);

	/**
	 * metadata id로 Metadata 테이블과 Program 테이블을 조인해서 가져옴
	 * @param metaId Metadata와 Program의 조인키
	 * @return 조인값을 리턴
	 */
	public Metadata getMetadataAndProgramUsingId(Integer metaId);

	/**
	 * utterance_id 를 이용하여 eojeollist 데이터 가져오기
	 * @param id eojeollist 테이블의 utterance_id 값
	 * @return utterance_id 값에 해당하는 eojeollist 테이블들의 값을 리스트에 담아 리턴
	 */
	public List<EojeolList> getEojeolListUsingUtteranceId(String id);

	/**
	 * JsonLog 테이블을 모두 가져옴
	 * @param function_name 페이지의 번호를 클릭했을 때 호출되는 자바스크립트 함수명 또는 게시글 조회를 요청하는 함수명을 저장할 변수
	 * @param current_page_no 현재 화면에 출력되고 있는 페이지 번호 또는 페이지의 번호를 클릭했을 때에 번호를 저장할 변수
	 * @param count_per_page 한 화면에 출력되는 페이지의 수를 저장할 변수
	 * @param count_per_list 한 화면에 출력되는 게시글의 수를 저장할 변수
	 * @param search_word 검색어
	 * @return JsonLog 테이블들의 값을 리스트로 담아 리턴
	 */
	public ResponseData getJsonLog(String function_name, 
									int current_page_no,
									int count_per_page,
									int count_per_list,
									String search_word);

	/**
	 * 음성데이터 저장 디렉토리에가서 프론트에서 음성파일에 접근할 수 있도록 webapp/resource/sound/로 음성파일 복사
	 * @param metaTitle 사용자가 클릭한 utterance의 파일명
	 * @param request 사용자로부터 들어온 요청
	 */
	public void wavFileCopy(String metaTitle, HttpServletRequest request);

	/**
	 * wav 파일들을 저장 경로에 업로드
	 * @param wavFile wav 파일
	 * @throws Exception 파일 업로드 예외처리
	 */
	public void uploadWav(List<MultipartFile> wavFile) throws Exception;

	/**
	 * utterance 수정
	 * @param id 수정할 utterance 의 id
	 * @param form 바꿀 문장
	 * @return 수정 완료 여부
	 */
	public boolean editUtterance(String id, String form);

	/**
	 * 문장 수정 이력관리 테이블을 페이징 처리
	 * @param metadata_id 불러올 metadata id
	 * @param function_name 페이지의 번호를 클릭했을 때 호출되는 자바스크립트 함수명 또는 게시글 조회를 요청하는 함수명을 저장할 변수
	 * @param current_page_no 현재 화면에 출력되고 있는 페이지 번호 또는 페이지의 번호를 클릭했을 때에 번호를 저장할 변수
	 * @param count_per_page 한 화면에 출력되는 페이지의 수를 저장할 변수
	 * @param count_per_list 한 화면에 출력되는 게시글의 수를 저장할 변수
	 * @param search_word 검색어
	 * @return 페이징 처리된 테이블
	 */
	public ResponseData getUtteranceLog(int metadata_id,
										String function_name,
										int current_page_no,
										int count_per_page,
										int count_per_list,
										String search_word);

	/**
	 * utteranceLog 데이터를 no로 가져옴
	 * @param no 가져올 데이터의 no
	 * @return utteranceLog
	 */
	public UtteranceLog getUtteranceLogByUsingNo(int no);
}
