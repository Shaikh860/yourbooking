package com.yourhome.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "training_centers")
public class TrainingCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Center name is required")
    @Size(max = 40, message = "Center name must be less than 40 characters")
    @Column(name = "center_name")
    private String centerName;

    @NotBlank(message = "Center code is required")
    @Pattern(regexp = "\\w{12}", message = "Center code must be exactly 12 characters alphanumeric")
    @Column(name = "center_code")
    private String centerCode;

    @Valid
    @Embedded
    private Address address;

    @NotNull(message = "Student capacity is required")
    @Min(value = 0, message = "Student capacity must be a positive number")
    @Column(name = "student_capacity")
    private Integer studentCapacity;

    @ElementCollection
    @NotEmpty(message = "At least one course must be offered")
    @CollectionTable(name = "center_courses", joinColumns = @JoinColumn(name = "center_id"))
    @Column(name = "course")
    private List<String> coursesOffered;

    @Column(name = "created_on")
    private Long createdOn;

    @Email(message = "Invalid email format")
    @Column(name = "contact_email")
    private String contactEmail;

    @NotBlank(message = "Contact phone is required")
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number format")
    @Column(name = "contact_phone")
    private String contactPhone;

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getStudentCapacity() {
        return studentCapacity;
    }

    public void setStudentCapacity(Integer studentCapacity) {
        this.studentCapacity = studentCapacity;
    }

    public List<String> getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(List<String> coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
