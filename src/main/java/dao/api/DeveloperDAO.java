package dao.api;

import entities.Developer;
import entities.Project;
import entities.Skill;

import java.math.BigDecimal;
import java.util.List;

public interface DeveloperDAO extends GenericDAO<Developer, Long> {
    BigDecimal getSumOfDevelopersSalaryOfProject(Project project);
    List<Developer> getDevelopersByProject(Project project);
    List<Developer> getDevelopersBySkillName (String skillName);
    List<Developer> getDevelopersBySkillLevel (Skill skillLevel);
}
