package dev.agasen.ecomm.framework.adapters.output.mysql.data;

import java.util.ArrayList;
import java.util.List;

import dev.agasen.ecomm.framework.adapters.output.mysql.shared.BaseEntityAudit;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "reviews")
@Entity(name = "reviews")
public class ReviewData extends BaseEntityAudit {

  private String tag;
  private String description;

  @Embedded
  private RatingData rating;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ImagePathData> imagePaths = new ArrayList<>();
  
}
