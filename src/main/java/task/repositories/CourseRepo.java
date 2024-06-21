package task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import task.entities.Course;

public interface CourseRepo extends JpaRepository<Course,Integer> {

}
