package wcc.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "audit_request")
public class AuditRequest {
	
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "ID")
	private Long id;
	
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Column(name = "postcode1")
	private String postcode1;
	
    @Column(name = "postcode2")
	private String postcode2;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPostcode1() {
		return postcode1;
	}
	public void setPostcode1(String postcode1) {
		this.postcode1 = postcode1;
	}
	public String getPostcode2() {
		return postcode2;
	}
	public void setPostcode2(String postcode2) {
		this.postcode2 = postcode2;
	}
}
