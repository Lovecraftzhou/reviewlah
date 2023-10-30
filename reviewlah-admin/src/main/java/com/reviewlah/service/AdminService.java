package com.reviewlah.service;

import java.math.BigInteger;

import com.reviewlah.db.pojo.Administrator;

public interface AdminService {

    void insertAdministrator(Administrator administrator);

    Administrator selectAdministratorById(BigInteger admin_id);

    void updateAdministrator(Administrator administrator);

    void deleteAdministratorById(BigInteger admin_id);

    Administrator selectAdministratorByName(String name);
}
