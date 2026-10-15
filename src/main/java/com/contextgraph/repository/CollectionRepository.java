package com.contextgraph.repository;
import com.contextgraph.model.CollectionItem; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List; import java.util.Optional;
public interface CollectionRepository extends JpaRepository<CollectionItem,Long>{ List<CollectionItem> findByOwnerIdOrderByCreatedAtDesc(Long ownerId); Optional<CollectionItem> findByIdAndOwnerId(Long id,Long ownerId); }
