package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.dto.QuestionerDTO;
import com.svyter.spring.swimingbysvyter.dto.QuestionerEditDTO;
import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;

public interface QuestionerService {
     void createQuestioner(QuestionerEditDTO questionerEditDTO, String token);
     ResponseDTO<QuestionerDTO> editQuestioner(QuestionerEditDTO questionerEditDTO, String token );
     ResponseDTO<QuestionerDTO> getQuestioner(String token);
     void delQuestioner(Long idQuest);
}
