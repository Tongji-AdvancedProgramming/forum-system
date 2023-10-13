package org.tongji.programming.service;

import org.tongji.programming.pojo.Student;

/**
 * @author cinea
 */
public interface StudentService {
    boolean isInfoCompletionNeeded(String userId);

    Student getMe(String userId);
}
