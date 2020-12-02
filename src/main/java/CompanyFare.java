import lombok.Data;

import java.io.Serializable;

@Data
public class CompanyFare implements Serializable {
  private Double tip;
  private Double total;
  private String companyName;
}
