package com.reviewlah.db.dao;

import java.math.BigInteger;

import com.reviewlah.db.pojo.Administrator;

public interface AdministratorDao {
    void insertAdministrator(Administrator administrator);

    Administrator selectAdministratorById(BigInteger admin_id);

    void updateAdministrator(Administrator administrator);

    void deleteAdministratorById(BigInteger admin_id);

    Administrator selectAdministratorByName(String name);
}
