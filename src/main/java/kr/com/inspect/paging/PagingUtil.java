package kr.com.inspect.paging;

/**
 * 페이지 네비게이션 정보 설정을 위한 클래스
 * @author Yeonhee Kim
 * @version 1.0
 */
public class PagingUtil {
	
	/**
	 * 페이지 네비게이션 정보 설정
	 * @param commonForm 페이징 처리된 화면에 필요한 변수를 담고 있는 Form
	 * @return 페이징 처리에 필요한 정보를 담아서 화면으로 보낼 DTO
	 */
	public static CommonDto setPageUtil(CommonForm commonForm) {
		CommonDto commonDto = new CommonDto();
		
		String pagination = ""; // 페이징 결과 값
		String functionName = commonForm.getFunction_name(); // 페이징 목록을 요청하는 자바스크립트 함수명
		int currentPage = commonForm.getCurrent_page_no(); // 현재 페이지 번호
		int countPerList = commonForm.getCount_per_list(); // 한 화면에 출력될 게시물 수
		int countPerPage = commonForm.getCount_per_page(); // 한 화면에 출력될 페이지 수
		int totalListCount = commonForm.getTatal_list_count(); // 총 게시물 수
		
		/* 총 페이지 수 세팅 */
		int totalPageCount = totalListCount / countPerList; // 총 페이지 수
		/* 총 페이수를 구할 때 int형으로 계산하면 나머지가 있는 경우 위에서 구한 총 페이지 수에서 1을 더함 */
		if (totalListCount % countPerList > 0) {
			totalPageCount = totalPageCount + 1;
        }
		
		/* 한 화면에 첫 페이지 번호 세팅 */
		int viewFirstPage = (((currentPage - 1) / countPerPage) * countPerPage) + 1; 
		/* 한 화면에 마지막 페이지 번호 세팅 */
		int ViewLastPage = viewFirstPage + countPerPage - 1; 
		/* 마지막 페이지의 수가 총 페이지의 수보다 큰 경우는 마지막 페이지의 수에 총 페이지의 수 할당 */
		if (ViewLastPage > totalPageCount) { 
			ViewLastPage = totalPageCount;
        }
		
		int totalFirstPage = 1; // 전체 페이지 중에 처음 페이지
		int totalLastPage = totalPageCount; // 전체 페이지 중에 마지막 페이지
		int prePerPage = 0; // 이전 화면에 첫번째 번호
		if (viewFirstPage - countPerPage > 0) {
			prePerPage = viewFirstPage - countPerPage;
		} else {
			prePerPage = totalFirstPage;
        }
		int nextPerPage = 0; // 이후 화면에 첫번째 번호
		if (viewFirstPage + countPerPage < totalPageCount) {
			nextPerPage = viewFirstPage + countPerPage;
		} else {
			nextPerPage = totalPageCount;
        }
		
		// 페이지 네이게이션 설정
		pagination += "<div class='pagination'>";
		pagination += "<a href='javascript:" + functionName + "(\"" + totalFirstPage + "\");' class=\"direction_left01\">[<<]</a>";
		pagination += "<a href='javascript:" + functionName + "(" + prePerPage + ");' class=\"direction_left01\">[<]</a>";
		for (int a = viewFirstPage; a <= ViewLastPage; a++) {
			if (a == currentPage) {
				pagination += "<a href='javascript:" + functionName + "(\"" + a + "\");' class='on_page'>[" + a + "]</a>";
			} else {
				pagination += "<a href='javascript:" + functionName + "(\"" + a + "\");' class='other_page'>[" + a + "]</a>";
            }
        }
		pagination += "<a href='javascript:" + functionName + "(" + nextPerPage + ");' class=\"direction_right01\">[>]</a>";
		pagination += "<a href='javascript:" + functionName + "(" + totalLastPage + ");' class=\"direction_right01\">[>>]</a>";
		pagination += "</div>";
		
		int offset = ((currentPage - 1) * countPerList); // 한 화면의 표출되는 게시물의 시작 번호 (쿼리 조건절)
		
		// LIMIT는 가져올 row의 수, OFFSET은 몇 번째 row부터 가져올지를 결정
		commonDto.setLimit(countPerList);
		commonDto.setOffset(offset);
		commonDto.setPagination(pagination);
		
		return commonDto;
    }
}
