package org.tongji.programming.service;

import org.tongji.programming.pojo.Student;

import java.util.List;

/**
 * @author cinea
 */
public interface UserService {
    boolean isInfoCompletionNeeded(String userId);

    Student getMe(String userId);
}
