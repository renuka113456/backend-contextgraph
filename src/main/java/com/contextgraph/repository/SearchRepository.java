package com.contextgraph.repository;
import com.contextgraph.model.SearchEntry; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface SearchRepository extends JpaRepository<SearchEntry,Long>{ List<SearchEntry> findTop30ByOwnerIdOrderByCreatedAtDesc(Long ownerId); }
