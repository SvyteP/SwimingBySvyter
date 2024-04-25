package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.model.QuestionerModel;

public interface QuestionerService {
     void createQuestioner(QuestionerModel questionerModel);
     void editQuestioner(QuestionerModel questionerModel, Long id);
     QuestionerModel getQuestioner(Long idCustomers);
     void delQuestioner(Long idCustomers);
}
