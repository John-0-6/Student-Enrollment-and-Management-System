package com.johnrovin.studentenrollmentandmanagementsystem.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class IdGenerator {


  private static int studentCounter = 1;

  private IdGenerator() {

  }

  public static String nextStudentId() {
    String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    String counterPart = String.format("%03d", studentCounter++);

    return datePart + "-" + counterPart;
  }
}