package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.getstream.annotations.Query;
import java.util.Date;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetRepliesRequest {

  @Query("limit")
  @JsonIgnore
  private Integer Limit;

  @Query("offset")
  @JsonIgnore
  private Integer Offset;

  @Query("id_gte")
  @JsonIgnore
  private String IDGte;

  @Query("id_gt")
  @JsonIgnore
  private String IDGt;

  @Query("id_lte")
  @JsonIgnore
  private String IDLte;

  @Query("id_lt")
  @JsonIgnore
  private String IDLt;

  @Query("created_at_after_or_equal")
  @JsonIgnore
  private Date CreatedAtAfterOrEqual;

  @Query("created_at_after")
  @JsonIgnore
  private Date CreatedAtAfter;

  @Query("created_at_before_or_equal")
  @JsonIgnore
  private Date CreatedAtBeforeOrEqual;

  @Query("created_at_before")
  @JsonIgnore
  private Date CreatedAtBefore;

  @Query("id_around")
  @JsonIgnore
  private String IDAround;

  @Query("created_at_around")
  @JsonIgnore
  private Date CreatedAtAround;

  @Query("sort")
  @JsonIgnore
  private List<SortParamRequest> Sort;
}
