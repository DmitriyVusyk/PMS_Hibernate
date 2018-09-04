package dao.hibenateImplementations;

import dao.api.DeveloperDAO;
import entities.Developer;
import entities.Project;
import entities.Skill;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.List;

public class HibernateDeveloperDAOImpl implements DeveloperDAO {

    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public void save(Developer developer) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(developer);

        transaction.commit();
        session.close();
    }

    public Developer getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Developer developer = session.get(Developer.class, id);
        return developer;
    }

    public void remove(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Developer developer = session.get(Developer.class, id);
        session.delete(developer);
        transaction.commit();
        session.close();
    }

    public List<Developer> getAll() {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM entities.Developer");
        List<Developer> result = query.list();
        session.close();
        return result;

    }


    public BigDecimal getSumOfDevelopersSalaryOfProject(Project project) {
        Session session = this.sessionFactory.openSession();
        String hql = "select sum(entities.Developer.salary) FROM entities.Developer d left join fetch d.projects p where p.id = :id";
        List<Developer> resultList = session.createQuery(hql, Developer.class).setLong("id", project.getId()).getResultList();
        Developer resultDeveloper = resultList.get(1);
        BigDecimal result = resultDeveloper.getSalary();
        session.close();
        return result;
    }

    public List<Developer> getDevelopersByProject(Project project) {
        Session session = this.sessionFactory.openSession();
        String hql = "FROM entities.Developer d left join fetch d.projects p where p.id = ? ";
        List<Developer> result = session.createQuery(hql).setParameter(0, project.getId()).list();
        session.close();
        return result;
    }

    public List<Developer> getDevelopersBySkillName(String skillName) {
        Session session = this.sessionFactory.openSession();
        String hql = "FROM entities.Developer d left join fetch d.skill s where s.skillName = :skillName ";
        List<Developer> result = session.createQuery(hql).setParameter("skillName", skillName).list();
        session.close();
        return result;
    }

    public List<Developer> getDevelopersBySkillLevel(Skill skillLevel) {
        Session session = this.sessionFactory.openSession();
        String hql = "FROM entities.Developer d left join fetch d.skill s where s.skillLevel = :skillLevel ";
        List<Developer> result = session.createQuery(hql).setParameter("skillLevel", skillLevel).list();
        session.close();
        return result;
    }
}
