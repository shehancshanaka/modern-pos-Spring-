package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.db.JPAUtil;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerBOImpl implements CustomerBO {

    @Autowired
    private CustomerDAO customerDAO;

    EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

//    public CustomerBOImpl(){
//        String dao = DAOFactory.getInstance().<String>getDAO(DAOTypes.CUSTOMER);
//    }

    @Override
    public CustomerDTO getCustomerById(String id) throws Exception {
//        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
            customerDAO.setEntityManager(entityManager);
            Customer customer = customerDAO.find(id);
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
            entityManager.getTransaction().commit();
            return customerDTO;

    }

    public List<CustomerDTO> getAllCustomers() throws Exception {
//        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
            customerDAO.setEntityManager(entityManager);
            List<CustomerDTO> customers = customerDAO.findAll().stream().map(customer -> new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress())).collect(Collectors.toList());
            entityManager.getTransaction().commit();
            return customers;

    }

    public void saveCustomer(CustomerDTO dto) throws Exception {
//        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
            customerDAO.setEntityManager(entityManager);
            customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getAddress()));
            entityManager.getTransaction().commit();

    }

    public void updateCustomer(CustomerDTO dto) throws Exception {
//        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
            customerDAO.setEntityManager(entityManager);
            customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getAddress()));
            entityManager.getTransaction().commit();

    }

    public void removeCustomer(String id) throws Exception {
//        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
            customerDAO.setEntityManager(entityManager);
            customerDAO.delete(id);
            entityManager.getTransaction().commit();

    }

}
