package com.dxc.librarymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.librarymanagement.dao.TuanDAO;

@Service
@Transactional
public class UserServiceImpl {

    @Autowired
    private TuanDAO tuanDAO;


}
