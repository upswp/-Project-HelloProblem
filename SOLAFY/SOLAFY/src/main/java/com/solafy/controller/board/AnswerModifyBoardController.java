package com.solafy.controller.board;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solafy.model.AnswerModifyBoardDto;
import com.solafy.service.board.AnswerModifyBoardService;

import io.swagger.annotations.ApiOperation;

/**
 * @FileName : AnswerModifyBoardController.java
 * @Project : SOLAFY
 * @Date : 2020. 12. 22
 * @작성자 : BUMSEOK SEO
 * 
 * @변경이력 :
 * @프로그램 설명 : 답안수정 게시판 컨트롤러
 */

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/answermodify")

public class AnswerModifyBoardController {
	private static final Logger logger = LoggerFactory.getLogger(AnswerModifyBoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private AnswerModifyBoardService answerModifyBoardService;

	/**
	 * @param answerModifyBoardDto 등록할 게시글 정보
	 * @return 성공여부에 따른 반환값(SUCCESS, FAIL)
	 * @Method 설명 : 게시글 정보를 받아서 등록한다
	 * @변경이력 :
	 */
	@ApiOperation(value = "답안수정 게시판에 게시글을 등록한다.", response = String.class)
	@PostMapping(value = "/createArticle")
	public ResponseEntity<String> createArticle(@RequestBody AnswerModifyBoardDto answerModifyBoardDto) {
		logger.info("createArticle - 호출" + new Date());
		if (answerModifyBoardService.createArticle(answerModifyBoardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * @return List<AnswerModifyBoardDto> 답안수정 게시판의 모든 게시글 반환
	 * @Method 설명 : 답안수정 게시판의 모든 게시글을 반환
	 * @변경이력 :
	 */
	@ApiOperation(value = "답안수정 게시판의 모든 게시글을 반환한다.", response = List.class)
	@GetMapping(value = "/selectAllArticles")
	public ResponseEntity<List<AnswerModifyBoardDto>> selectAllArticles() {
		logger.info("selectArtilces - 호출" + new Date());
		return new ResponseEntity<List<AnswerModifyBoardDto>>(answerModifyBoardService.selectAllArticles(),
				HttpStatus.OK);
	}

	/**
	 * @param articleNo 반환하고 싶은 게시글의 번호
	 * @return freeBoardDto
	 * @Method 설명 : 게시글 번호에 해당하는 게시글을 반환한다.
	 * @변경이력 : 2021-01-01(BUMSEOK SEO) : 해당 메서드 추가
	 */
	@ApiOperation(value = "게시글 번호에 해당하는 게시글을 반환한다", response = AnswerModifyBoardDto.class)
	@GetMapping(value = "/selectArticleByArticleNo/{articleNo}")
	public AnswerModifyBoardDto selectArticleByArticleNo(@PathVariable int articleNo) {
		logger.info("selectAtricleByArticleNo - 호출" + new Date());
		AnswerModifyBoardDto answerModifyBoardDto = answerModifyBoardService.selectArticleByArticleNo(articleNo);
		return answerModifyBoardDto;
	}

	/**
	 * @param title 제목 검색을 위한 키워드
	 * @return List<AnswerModifyBoardDto> 게시글 list
	 * @Method 설명 : 입력한 키워드를 포함하는 제목의 게시글들을 반환한다.
	 * @변경이력 :
	 */
	@ApiOperation(value = "입력한 키워드를 포함하는 제목의 게시글을 반환한다", response = List.class)
	@GetMapping(value = "/selectArticleByTitle/{title}")
	public List<AnswerModifyBoardDto> selectArticleByTitle(@PathVariable String title) {
		logger.info("selectAtricleByTitle - 호출" + new Date());
		List<AnswerModifyBoardDto> list = answerModifyBoardService.selectArticleByTitle(title);
		return list;
	}

	/**
	 * @param String nickname 닉네임 검색을 위한 키워드
	 * @return List<AnswerModifyBoardDto> 게시글 list
	 * @Method 설명 : 입력한 닉네임과 일치하는 작성자의 게시글들을 반환한다.
	 * @변경이력 :
	 */
	@ApiOperation(value = "입력한 닉네임과 일치하는 작성자의 게시글을 반환한다", response = List.class)
	@GetMapping(value = "/selectArticleByNickname/{nickname}")
	public List<AnswerModifyBoardDto> selectArticleByNickname(@PathVariable String nickname) {
		logger.info("selectAtricleByTitle - 호출" + new Date());
		List<AnswerModifyBoardDto> list = answerModifyBoardService.selectArticleByNickname(nickname);
		return list;
	}

	/**
	 * @param int problemNo 문제번호 검색을 위한 int
	 * @return List<AnswerModifyBoardDto> 게시글 list
	 * @Method 설명 : 입력한 문제번호에 해당하는 게시글들을 반환한다.
	 * @변경이력 :
	 */
	@ApiOperation(value = "입력한 문제번호에 해당하는 게시글을 반환한다", response = List.class)
	@GetMapping(value = "/selectArticleByProblemNo/{problemNo}")
	public List<AnswerModifyBoardDto> selectArticleByProblemNo(@PathVariable int problemNo) {
		logger.info("selectAtricleByTitle - 호출" + new Date());
		List<AnswerModifyBoardDto> list = answerModifyBoardService.selectArticleByProblemNo(problemNo);
		return list;
	}

	/**
	 * @param answerModifyBoardDto 게시글 수정을 위한 정보
	 * @return 성공여부에 따른 반환값(SUCCESS, FAIL)
	 * @Method 설명 : 게시글의 수정 정보를 입력받아 게시글 수정한다
	 * @변경이력 :
	 */
	@ApiOperation(value = "답안수정 게시판의 게시글을 수정한다", response = String.class)
	@PostMapping(value = "/updateArticle")
	public ResponseEntity<String> updateArticle(@RequestBody AnswerModifyBoardDto answerModifyBoardDto) {
		logger.info("updateArticle - 호출" + new Date());
		if (answerModifyBoardService.updateArticle(answerModifyBoardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			// TODO: responseEntity의 의미
			return new ResponseEntity<String>(FAIL, HttpStatus.NOT_MODIFIED);
		}

	}

	/**
	 * @param articleNo 삭제할 게시글의 게시글번호
	 * @return 성공여부에 따른 반환값(SUCCESS, FAIL)
	 * @Method 설명 : 게시글 번호에 해당하는 게시글을 삭제한다
	 * @변경이력 :
	 */
	@ApiOperation(value = "답안수정 게시판의 게시글을 삭제한다", response = String.class)
	@DeleteMapping(value = "/deleteArticle/{articleNo}")
	public ResponseEntity<String> deleteArticle(@PathVariable int articleNo) {
		logger.info("deleteArticle - 호출" + new Date());
		if (answerModifyBoardService.deleteArticle(articleNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NOT_MODIFIED);
		}
	}

}
