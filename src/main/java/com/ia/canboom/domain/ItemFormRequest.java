package com.ia.canboom.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ItemFormRequest {
  private String title;
  private String description;
  private String assignee;
  private String priority;
  private String status;
  private LocalDate dateDue;
  private String tags;
  private Integer timeEstimate;
}
