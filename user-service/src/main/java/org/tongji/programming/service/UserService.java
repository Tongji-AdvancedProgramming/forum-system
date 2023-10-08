package org.tongji.programming.service;

import org.tongji.programming.pojo.Student;

/**
 * @author cinea
 */
public interface UserService {
    boolean isInfoCompletionNeeded(String userId);

    Student getMe(String userId);
}
