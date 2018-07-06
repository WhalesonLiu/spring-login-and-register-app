package com.kgregorczyk.library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kgregorczyk.library.audit.CreateAndLastModifiedDate;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"author"}, callSuper = true)
@Table(name = "article", indexes = {@Index(columnList = "title")})
public class Article extends CreateAndLastModifiedDate implements Serializable {
  private static final long serialVersionUID = -1621704437021384404L;

  @Id
  @GeneratedValue
  private Long id;

  @NotBlank
  @NotEmpty
  @Length(max = 255)
  private String title;

  @NotBlank
  @NotEmpty
  @Length(max = 400)
  private String shortDescription;

  @NotBlank
  @NotEmpty
  @Length(max = 4000)
  private String description;

  @NotNull
  @ManyToOne(optional = false)
  @JsonManagedReference
  private User author;
}
