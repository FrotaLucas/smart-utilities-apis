package project.demo.repository.ReadingRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.demo.model.Reading;

public interface ReadingRepository extends JpaRepository<Reading, Long>
{
    
}
