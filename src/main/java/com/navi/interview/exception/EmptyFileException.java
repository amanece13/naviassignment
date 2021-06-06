package com.navi.interview.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class EmptyFileException extends RuntimeException{

    private Long timestamp;
    private HttpStatus status;
    private String error;
    private String message;
    private Boolean success;

}
