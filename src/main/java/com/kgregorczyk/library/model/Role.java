package com.kgregorczyk.library.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role", indexes = {@Index(columnList = "name", unique = true)})
public class Role implements Serializable {
  private static final long serialVersionUID = 768193107575285028L;

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  /*@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
  @JsonManagedReference
  private Set<User> users;*/
}
