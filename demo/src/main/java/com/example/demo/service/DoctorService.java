package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void addPatient(User user){
        doctorRepository.addPatient(user);
    }
}
