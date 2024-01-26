package com.madeeasy.dao.impl;

import com.madeeasy.dao.UserDAO;
import com.madeeasy.entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory = null;

    public UserDAOImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public void save(Users user) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            try {
                session.persist(user);
                transaction.commit();
                System.out.println("User saved successfully !!");
            } catch (ConstraintViolationException exception) {
                System.out.println("Constraint Violation : " + exception.getMessage());
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Users findByEmail(String email) {
        try (Session session = this.sessionFactory.openSession()) {
            String sql = "SELECT * FROM tbl_users WHERE email = :email";
            NativeQuery<Users> nativeQuery = session.createNativeQuery(sql, Users.class);
            nativeQuery.setParameter("email", email);
            return nativeQuery.getSingleResult();
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteByEmail(String email) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            try {
                String sql = "DELETE FROM tbl_users WHERE email = :email";
                NativeQuery<Users> nativeQuery = session.createNativeQuery(sql, Users.class);
                nativeQuery.setParameter("email", email);
                nativeQuery.executeUpdate();
                transaction.commit();
                System.out.println("User deleted successfully!!");
            } catch (Exception e) {
                // Rollback the transaction if an exception occurs
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                // Log or rethrow the exception for better debugging
                System.out.println("Exception : " + e.getMessage());
            }
        } catch (Exception e) {
            // Rollback the transaction if an exception occurs
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            // Log or rethrow the exception for better debugging
            System.out.println("Exception : " + e.getMessage());
        }
    }

    @Override
    public List<Users> getAllUsers() {
        try (Session session = this.sessionFactory.openSession()) {
            // using native SQL query to select all customers
            String sql = "SELECT * FROM tbl_users";
            NativeQuery<Users> nativeQuery = session.createNativeQuery(sql, Users.class);
            return nativeQuery.getResultList();
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Users user) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            try {
                session.merge(user);
                transaction.commit();
                System.out.println("User updated successfully !!");
            } catch (ConstraintViolationException exception) {
                System.out.println("Constraint Violation : " + exception.getMessage());
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
            }
        } catch (Exception e) {
            System.out.println("Exception : {}" + e.getMessage());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
