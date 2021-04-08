package wcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import wcc.domain.PostcodeLocation;

@Repository
public interface PostcodeLocationRepository extends JpaRepository<PostcodeLocation, Long>  {
	
	public PostcodeLocation findByPostcode(String postcode);
	
	@Modifying
	@Query("update PostcodeLocation u set u.latitude = ?2, u.longitude = ?3 where u.postcode = ?1")
	public void updateByPostcode(String postcode, Double  latitude, Double longitude);

}
