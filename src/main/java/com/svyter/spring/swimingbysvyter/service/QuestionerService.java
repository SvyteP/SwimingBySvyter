package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.dto.QuestionerDTO;

public interface QuestionerService {
     void createQuestioner(QuestionerDTO questionerDTO);
     void editQuestioner(QuestionerDTO questionerDTO, Long id);
     QuestionerDTO getQuestioner(Long idCustomers);
     void delQuestioner(Long idQuest);
}
