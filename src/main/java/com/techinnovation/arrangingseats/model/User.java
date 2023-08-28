package com.techinnovation.arrangingseats.model;

import java.util.HashSet;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(columnNames = "loginId"),
})
public class User {

  @Id
  @NotBlank
  @Size(max = 11)
  private String loginId;

  @NotBlank
  @Size(max = 20)
  private String username;

  @Size(max = 120)
  private String password;

  @Size(max = 50)
  private String department;

  @Size(max = 25)
  private String position;

  @Size(max = 30)
  private String project;

  public User() {};

}
