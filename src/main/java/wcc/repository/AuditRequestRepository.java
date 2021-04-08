package wcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wcc.domain.AuditRequest;
import wcc.domain.PostcodeLocation;

@Repository
public interface AuditRequestRepository extends JpaRepository<PostcodeLocation, Long>  {

	void save(AuditRequest ar);

}
