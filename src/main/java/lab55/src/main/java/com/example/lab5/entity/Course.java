package com.example.lab5.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @Column(name = "id")
    private Long id;
    @Column(name = "course_name")
    @NotEmpty
    private String name;
    @Column(name = "theme")
    @NotEmpty
    private String theme;
    @Column(name = "info")
    private String info;
    @Column(name = "start_date")
    @NotEmpty
    private LocalDate startDate;
    @Column(name = "end_date")
    @NotEmpty
    private LocalDate endDate;

    public Course(String name, String theme,
            String info, String startDate, String endDate) {
        this.name = name;
        this.theme = theme;
        this.info = info;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "teacher_courses",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private User teacher;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> students;

    public User getTeacher() {
        return teacher;
    }

    public Set<User> getStudents() {
        return students;
    }

    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }

    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate);
    }
}