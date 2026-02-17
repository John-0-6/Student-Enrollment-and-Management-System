package com.johnrovin.studentenrollmentandmanagementsystem.service;

import com.johnrovin.studentenrollmentandmanagementsystem.repository.StudentRepository;

import java.util.ArrayList;

public class Manager {

  ArrayList<StudentRepository> studentRepositories;

  public Manager(){
    studentRepositories = new ArrayList<>();
  }
}