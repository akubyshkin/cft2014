package cft2014.runner.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTS")
public class Client implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "second_name", nullable = true)
  private String secondName;

  @Column(name = "last_name", nullable = true)
  private String lastName;

  public Client() {
  }

  public Client(String firstName, String secondName, String lastName) {
    super();
    this.firstName = firstName;
    this.secondName = secondName;
    this.lastName = lastName;
  }

  public Integer getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
