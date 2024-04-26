package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.QuestionerRepo;
import com.svyter.spring.swimingbysvyter.entity.Questioner;
import com.svyter.spring.swimingbysvyter.model.QuestionerModel;
import com.svyter.spring.swimingbysvyter.service.QuestionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionerServiceImpl implements QuestionerService {
    private QuestionerRepo questionerRepo;
    @Autowired
    public QuestionerServiceImpl(QuestionerRepo questionerRepo) {
        this.questionerRepo = questionerRepo;
    }

    @Override
    public void createQuestioner(QuestionerModel questionerModel) {
        try {

            Questioner questioner = new Questioner(questionerModel.getLangthPool(), questionerModel.getGender(), questionerModel.getAge(),
                    questionerModel.getLevelTrain(), questionerModel.getTimeTrain(), questionerModel.getCountWeek(),
                    questionerModel.getCountTrainOneWeek());
            questionerRepo.save(questioner);

        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void editQuestioner(QuestionerModel questionerModel, Long id) {
        try {
           Questioner questioner = questionerRepo.findById(id).orElseThrow();
            questioner.setLangthPool(questionerModel.getLangthPool());
            questioner.setGender(questionerModel.getGender());
            questioner.setAge(questionerModel.getAge());
            questioner.setLevelTrain(questionerModel.getLevelTrain());
            questioner.setTimeTrain(questionerModel.getTimeTrain());
            questioner.setCountWeek(questionerModel.getCountWeek());
            questioner.setCountTrainOneWeek(questionerModel.getCountTrainOneWeek());
            questionerRepo.save(questioner);
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e.getMessage());
        }

    }

    @Override
    public QuestionerModel getQuestioner(Long idCustomers) {
        try {
           return QuestionerModel.questionerConvertor(questionerRepo.findById(idCustomers).orElseThrow());
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void delQuestioner(Long idQuest) {
        try {
            questionerRepo.delete(questionerRepo.findById(idQuest).orElseThrow());
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e.getMessage());
        }

    }
}
