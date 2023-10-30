package com.reviewlah.service.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reviewlah.db.dao.AdministratorDao;
import com.reviewlah.db.pojo.Administrator;
import com.reviewlah.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdministratorDao administratorDao;

    @Override
    public void insertAdministrator(Administrator administrator) {
        administratorDao.insertAdministrator(administrator);
    }

    @Override
    public Administrator selectAdministratorById(BigInteger admin_id) {
        return administratorDao.selectAdministratorById(admin_id);
    }

    @Override
    public void updateAdministrator(Administrator administrator) {
        administratorDao.updateAdministrator(administrator);
    }

    @Override
    public void deleteAdministratorById(BigInteger admin_id) {
        administratorDao.deleteAdministratorById(admin_id);
    }

    @Override
    public Administrator selectAdministratorByName(String name) {
        return administratorDao.selectAdministratorByName(name);
    }
}
