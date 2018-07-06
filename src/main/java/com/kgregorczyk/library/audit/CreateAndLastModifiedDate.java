package com.kgregorczyk.library.audit;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CreateAndLastModifiedDate {

  @CreatedDate
  @Temporal(TIMESTAMP)
  protected Date creationDate;

  @LastModifiedDate
  @Temporal(TIMESTAMP)
  protected Date lastModifiedDate;


}
