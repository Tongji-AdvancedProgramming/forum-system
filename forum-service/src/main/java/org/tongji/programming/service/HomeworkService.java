package org.tongji.programming.service;

import org.tongji.programming.pojo.Homework;

/**
 * @author cinea
 */
public interface HomeworkService {
    Homework getHomework(String term, Integer hwId);
}
