package com.contextgraph.model;
import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="search_history")
public class SearchEntry {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="owner_id",nullable=false) private AppUser owner;
 @Column(nullable=false,length=500) private String query; @Column(nullable=false) private Instant createdAt=Instant.now();
 public Long getId(){return id;} public AppUser getOwner(){return owner;} public void setOwner(AppUser v){owner=v;} public String getQuery(){return query;} public void setQuery(String v){query=v;} public Instant getCreatedAt(){return createdAt;}
}
