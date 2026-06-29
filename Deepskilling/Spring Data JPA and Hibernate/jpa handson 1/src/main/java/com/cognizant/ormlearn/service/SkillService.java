package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.repository.SkillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SkillService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillService.class);
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Transactional
    public Skill get(int id) {
        LOGGER.info("Start");
        Skill skill = skillRepository.findById(id).orElseThrow();
        LOGGER.info("End");
        return skill;
    }

    @Transactional
    public void save(Skill skill) {
        LOGGER.info("Start");
        skillRepository.save(skill);
        LOGGER.info("End");
    }
}
