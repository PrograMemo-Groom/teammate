package teammate.teammate.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import teammate.teammate.controller.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 기타 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(Exception ex) {
        ApiResponse<?> response = ApiResponse.createError("알 수 없는 오류 : " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 파라미터로 받은 값의 인자가 맞지 않을 때
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResponse<?> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String message = String.format("잘못된 요청: '%s' 값을 '%s' 타입으로 변환할 수 없습니다.", e.getValue(), e.getRequiredType().getSimpleName());
        return ApiResponse.createError(message);
    }

    // 요구되는 값이 비어있을 때
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse<?>> handleNullPointerException(NullPointerException ex) {
        ApiResponse<?> response = ApiResponse.createError("필수 데이터가 누락되었습니다.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
