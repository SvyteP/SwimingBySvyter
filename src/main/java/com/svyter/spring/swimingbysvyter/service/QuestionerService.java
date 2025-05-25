package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.dto.QuestionerDTO;
import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;

public interface QuestionerService {
     void createQuestioner(QuestionerDTO questionerDTO, Long id);
     ResponseDTO<QuestionerDTO> editQuestioner(QuestionerDTO questionerDTO, Long id);
     ResponseDTO<QuestionerDTO> getQuestioner(Long idCustomers);
     void delQuestioner(Long idQuest);
}
