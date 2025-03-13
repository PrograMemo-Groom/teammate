package teammate.teammate.exceptionHandler;

import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import teammate.teammate.controller.ApiResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 기타 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(Exception ex) {
        ApiResponse<?> response = ApiResponse.createError("알 수 없는 오류 요청 URL을 다시 확인해보십시오: " + ex.getMessage());
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 파라미터로 받은 값의 인자가 맞지 않을 때
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResponse<?> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String message = String.format("잘못된 요청: '%s' 값을 '%s' 타입으로 변환할 수 없습니다.", e.getValue(), e.getRequiredType().getSimpleName());
        return ApiResponse.createError(message);
    }

    // 숫자 변환 실패
    @ExceptionHandler(NumberFormatException.class)
    public ApiResponse<?> handleNumberFormatException(NumberFormatException e) {
        return ApiResponse.createError("숫자 변환 오류: " + e.getMessage());
    }

    // 데이터베이스 접근 오류
    @ExceptionHandler(DataAccessException.class)
    public ApiResponse<?> handleDataAccessException(DataAccessException e) {
        return ApiResponse.createError("데이터베이스 오류 발생: " + e.getMessage());
    }

    // 요구되는 값이 비어있을 때
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse<?>> handleNullPointerException(NullPointerException ex) {
        ApiResponse<?> response = ApiResponse.createError("필수 데이터가 누락되었습니다.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 유효하지 않은 요청 파라미터
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiResponse<?> response = ApiResponse.createError("잘못된 요청: " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 특정 예외를 명확히 처리
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ApiResponse<?>> handleNoResultException(NoResultException ex) {
        ApiResponse<?> response = ApiResponse.createError("데이터를 찾을 수 없습니다.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ApiResponse<?>> handleEmptyResultException(EmptyResultDataAccessException ex) {
        ApiResponse<?> response = ApiResponse.createError("결과가 존재하지 않습니다.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
