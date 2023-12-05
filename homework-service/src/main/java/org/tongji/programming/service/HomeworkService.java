package org.tongji.programming.service;

import org.tongji.programming.pojo.Homework;
import org.tongji.programming.pojo.HomeworkUploaded;

/**
 * @author cinea
 */
public interface HomeworkService {
    Homework getHomework(String term, Integer hwId);

    HomeworkUploaded getHomeworkUploaded
}
