/**
 * 
 */
package software.jsj.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author jsjackson
 *
 */
@Entity
@Table(name ="specialties")
public class Specialty extends BaseEntity {

  @Column(name = "description")
  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
}
