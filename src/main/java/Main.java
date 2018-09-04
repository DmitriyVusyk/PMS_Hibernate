import dao.hibenateImplementations.HibernateDeveloperDAOImpl;
import entities.Company;
import entities.Customer;
import entities.Developer;
import entities.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        Session session = HibernateUtil.createHibernateSession();

        Developer developer = new Developer();
        developer.setFirstName("Alex");
        developer.setLastName("Amoralex");
        developer.setSex("male");
        developer.setSalary(BigDecimal.valueOf(1000));
        List<Project> projects = new ArrayList<Project>();
        developer.setProjects(projects);

        Company company = new Company();
        company.setCompanyName("GlobalTech");
        company.setCompanyCity("Kiev");
        company.setCompanyCountry("Ukraine");
        company.setProjects(projects);

        Project project = new Project();
        project.setCost(BigDecimal.valueOf(20000));
        project.setProjectName("Some project");
        project.setProjectDescription("some project description");
        List<Developer> developers = new ArrayList<Developer>();
        project.setDevelopers(developers);

        Customer customer = new Customer();
        customer.setFirstName("Vasiliy");
        customer.setLastName("Pupkin");
        customer.setProjects(projects);

        developers.add(developer);
        projects.add(project);




        if (session != null) {
            System.out.println("session is created");
            // Добавление записей в таблицу
            Transaction trans = session.beginTransaction();
            session.save(developer);
            session.save(company);
            session.save(project);
            session.save(customer);
            session.flush();
            trans.commit();
            // Закрытие сессии
            session.close();
        } else {
            System.out.println("session is not created");
        }

        HibernateDeveloperDAOImpl hdi = new HibernateDeveloperDAOImpl();
        System.out.println(hdi.getSumOfDevelopersSalaryOfProject(project));
        //System.out.println(hdi.getDevelopersByProject(project));
    }
}
