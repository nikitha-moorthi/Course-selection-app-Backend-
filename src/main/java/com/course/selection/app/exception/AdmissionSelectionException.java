package com.course.selection.app.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdmissionSelectionException extends RuntimeException {
 private String message;
}
